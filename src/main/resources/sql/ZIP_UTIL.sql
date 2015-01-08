
CREATE OR REPLACE PACKAGE ZIP_UTIL as

	FUNCTION BLOB_TO_CLOB (blob_in IN BLOB) RETURN CLOB;
	FUNCTION CLOB_TO_BLOB(p_clob CLOB) RETURN BLOB;
	FUNCTION UNZIP_TO_BLOB(p_blob blob) RETURN BLOB;
	FUNCTION UNZIP_TO_CLOB(p_blob blob) RETURN CLOB;
	FUNCTION UNZIP_TO_XML(p_blob blob) RETURN XMLTYPE;
	FUNCTION COMPRESS_XML(p_clob CLOB) RETURN BLOB;
	FUNCTION COMPRESS_XMLTYPE(p_xmltype XMLTYPE) RETURN BLOB;
	procedure GET_PKG_VERSION(p_version OUT varchar2);
end ZIP_UTIL;
/


CREATE OR REPLACE PACKAGE BODY ZIP_UTIL as

FUNCTION BLOB_TO_CLOB (blob_in IN BLOB)
RETURN CLOB
AS
	v_clob    CLOB;
	v_varchar VARCHAR2(32767);
	v_start	 PLS_INTEGER := 1;
	v_buffer  PLS_INTEGER := 32767;
BEGIN
  IF (blob_in is null) THEN
    v_clob :=null;
  ELSE  
    DBMS_LOB.CREATETEMPORARY(v_clob, TRUE);
    FOR i IN 1..CEIL(DBMS_LOB.GETLENGTH(blob_in) / v_buffer)
    LOOP
       v_varchar := UTL_RAW.CAST_TO_VARCHAR2(DBMS_LOB.SUBSTR(blob_in, v_buffer, v_start));
       DBMS_LOB.WRITEAPPEND(v_clob, LENGTH(v_varchar), v_varchar);
       v_start := v_start + v_buffer;
    END LOOP;
	END IF;
   RETURN v_clob;
  
END BLOB_TO_CLOB;



FUNCTION CLOB_TO_BLOB(p_clob CLOB) RETURN BLOB
AS
  v_blob BLOB;
  v_in number;
  v_out number;
  v_lang Pls_Integer := 0;
  v_warning Pls_Integer := 0;
  v_length INTEGER;
BEGIN
  IF (p_clob is null) THEN
    v_blob :=null;
  ELSE
    v_in :=1;
    v_out :=1;
    DBMS_LOB.createtemporary(v_blob, TRUE);
    DBMS_LOB.converttoblob(v_blob,p_clob, DBMS_lob.getlength(p_clob),
      v_in,v_out, DBMS_LOB.default_csid,v_lang, v_warning);
  END IF;
  --Size of the blob..
  v_length := DBMS_LOB.getlength(v_blob);
  IF v_length IS NULL THEN
        dbms_output.put_line('LOB is null.');
    ELSE
        dbms_output.put_line('The length is '|| v_length);
    END IF;

  return v_blob;
  
  
END CLOB_TO_BLOB;


FUNCTION UNZIP_TO_BLOB(p_blob blob) RETURN BLOB
AS
BEGIN
  
return utl_compress.LZ_UNCOMPRESS(p_blob); 
  
END UNZIP_TO_BLOB;

FUNCTION UNZIP_TO_CLOB(p_blob blob) RETURN CLOB
AS
BEGIN
  
return BLOB_TO_CLOB(utl_compress.LZ_UNCOMPRESS(p_blob)); 
  
END UNZIP_TO_CLOB;

FUNCTION UNZIP_TO_XML(p_blob blob) RETURN XMLTYPE
AS
BEGIN
  
return xmltype(BLOB_TO_CLOB(utl_compress.LZ_UNCOMPRESS(p_blob))); 
  
END UNZIP_TO_XML;

FUNCTION COMPRESS_XML(p_clob CLOB) RETURN BLOB
AS
  l_compressed_blob BLOB;
BEGIN
   l_compressed_blob:= UTL_COMPRESS.LZ_COMPRESS(CLOB_TO_BLOB(p_clob), 6);
   RETURN l_compressed_blob;
   
END COMPRESS_XML;

FUNCTION COMPRESS_XMLTYPE(p_xmltype xmltype) RETURN BLOB
AS
  
BEGIN
   RETURN COMPRESS_XML(p_xmltype.getClobVal());
   
END COMPRESS_XMLTYPE;



end ZIP_UTIL;
/


