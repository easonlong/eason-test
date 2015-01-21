/*
#ident	"%W%"
*/ 

create or replace package DB_HOUSEKEEPING is

  procedure gather_stats;

  procedure delete_stats;

  procedure rotate_log_partition(p_table_name       in varchar2,
                               p_retention_period in integer);

  procedure GET_PKG_VERSION(p_version OUT varchar2);

  procedure get_db_pkg_manifest(p_result OUT sys_refcursor);

  function get_db_revision RETURN INTEGER;
  
  procedure purge_dps_cdr_xml_audit(p_version_count in integer);
  
  procedure dropObject(ObjType varchar2,ObjName varchar2);
  procedure alterTableAdd(TableName varchar2,ColName varchar2, ColType varchar2);
  procedure alterTableDrop(TableName varchar2,ColName varchar2);
  procedure modifyTable(TableName varchar2,ColName varchar2, command varchar2);
  procedure alterTableRename(TableName varchar2,oldName varchar2, newName varchar2);
  procedure addIndex(TableName varchar2, indexName varchar2, indexColumns varchar2);
  procedure addUniqueIndex(TableName varchar2, indexName varchar2, indexColumns varchar2);
  
end DB_HOUSEKEEPING;
/

create or replace package body DB_HOUSEKEEPING is

  procedure gather_stats as
  begin
    dbms_stats.gather_schema_stats(ownname => user, cascade => true);
  end gather_stats;

  procedure delete_stats as
  begin
    dbms_stats.delete_schema_stats(user);
  end delete_stats;

  procedure rotate_log_partition(p_table_name       in varchar2,
                                 p_retention_period in integer) as
  begin
    EXECUTE IMMEDIATE 'ALTER TABLE ' || p_table_name ||
                      ' TRUNCATE PARTITION P' ||
                      (MOD(TO_NUMBER(TO_CHAR(SYSDATE + 2, 'J')),
                           p_retention_period) + 1) ||' REUSE STORAGE UPDATE INDEXES';
  end rotate_log_partition;

procedure GET_PKG_VERSION(p_version OUT varchar2) as
begin
	p_version := '$Revision: 99772 $';
end GET_PKG_VERSION;

procedure get_db_pkg_manifest(p_result OUT sys_refcursor) as
 l_version varchar2(255);
 l_versions STRING255_ARRAY;
 i INTEGER;
begin
	l_versions := STRING255_ARRAY();
	i :=0 ;

	for pl in (select object_name, procedure_name from user_procedures where procedure_name='GET_PKG_VERSION') loop
		execute immediate 'begin '||pl.object_name ||'.'|| pl.procedure_name||'(:1); END;' USING OUT l_version;
		l_versions.extend;
		i := i + 1;
		l_versions(i) := pl.object_name || ', '|| l_version;
	end loop;
	
	OPEN p_result FOR
		SELECT * from table(cast(l_versions AS STRING255_ARRAY));
end get_db_pkg_manifest;
function maximum(p1 IN INTEGER, p2 IN INTEGER) RETURN INTEGER
AS
BEGIN
	IF p1 < p2 THEN
		RETURN p2;
	ELSE
		RETURN p1;
	END IF;
END maximum;

function get_db_revision RETURN INTEGER as
 l_version varchar2(255);
 l_num_version INTEGER;
 l_max_version INTEGER;
begin
	l_max_version := 0;

	-- scan the packages and get the latest repo revision
	for pl in (select object_name, procedure_name from user_procedures where procedure_name='GET_PKG_VERSION') loop
		execute immediate 'begin '||pl.object_name ||'.'|| pl.procedure_name||'(:1); END;' USING OUT l_version;
		BEGIN
			l_num_version := to_number(trim(replace(replace(l_version, '$', ''), 'Revision:','')));
		EXCEPTION WHEN VALUE_ERROR THEN
			dbms_output.put_line(l_version);
			dbms_output.put_line(trim(replace(replace(l_version, '$', ''), 'Revision:','')));
			l_num_version := 0;
		END;
		l_max_version := maximum(l_num_version, l_max_version);
	end loop;

	-- check the deployment revision
	SELECT MAX(LAST_CHANGED_REV) INTO L_NUM_VERSION FROM DB_SCHEMA_SVN_TAG;
	l_max_version := maximum(l_num_version, l_max_version);

	RETURN l_max_version;
end get_db_revision;


procedure purge_dps_cdr_xml_audit(p_version_count in integer) as
 
 begin
 
 	EXECUTE IMMEDIATE 'delete from DPS_CDR_XML_AUDIT d 
			WHERE EXISTS (select 1 from 
				(select OASYS_DEAL_ID, 
					TPS_CDS_VERSION, 
					LAST_UPDATE_DATE, 
					AUD_TIMESTAMP 
				 from (
					SELECT 	OASYS_DEAL_ID, 
						TPS_CDS_VERSION, 
						LAST_UPDATE_DATE, 
						AUD_TIMESTAMP,
						DENSE_RANK() OVER (PARTITION BY OASYS_DEAL_ID ORDER BY OASYS_DEAL_ID ,TPS_CDS_VERSION DESC NULLS LAST ) AS VERSION_RANK
					FROM 	DPS_CDR_XML_AUDIT
					WHERE 	oasys_deal_id in (
						select 	oasys_deal_id 
						from 	DPS_CDR_XML_AUDIT
						group by oasys_deal_id
					having count(oasys_deal_id)> :p1 )  )
				where  VERSION_RANK > :p2 ) tmp
			where tmp.OASYS_DEAL_ID=d.OASYS_DEAL_ID
			and   tmp.TPS_CDS_VERSION = d.TPS_CDS_VERSION
			and   tmp.LAST_UPDATE_DATE=d.LAST_UPDATE_DATE
			and   tmp.AUD_TIMESTAMP=d.AUD_TIMESTAMP)' USING p_version_count,p_version_count;
			
	commit;
                           
end purge_dps_cdr_xml_audit;

procedure dropObject(ObjType varchar2,ObjName varchar2)
AS
Typ_Pkg_Pcd_Fct_Expt exception;
pragma exception_init(Typ_Pkg_Pcd_Fct_Expt, -4043 );
Table_View_Expt exception;
pragma exception_init(Table_View_Expt, -942 );
Sequence_Expt exception;
pragma exception_init(Sequence_Expt, -2289 );
Index_Expt exception;
pragma exception_init(Index_Expt, -1418 );
Trigger_Expt exception;
pragma exception_init(Trigger_Expt, -4080 );

begin
  execute immediate 'drop ' || ObjType ||' ' || ObjName;
  DBMS_OUTPUT.PUT_LINE(ObjType ||' ' || ObjName || ' Dropped!');
  EXCEPTION
    when Typ_Pkg_Pcd_Fct_Expt THEN
      DBMS_OUTPUT.PUT_LINE(ObjType ||' ' || ObjName || ' Does NOT exist!');
    when Table_View_Expt THEN
      DBMS_OUTPUT.PUT_LINE(ObjType ||' ' || ObjName || ' Does NOT exist!');
    when Sequence_Expt THEN
     DBMS_OUTPUT.PUT_LINE(ObjType ||' ' || ObjName || ' Does NOT exist!');	 
    when Index_Expt THEN
     DBMS_OUTPUT.PUT_LINE(ObjType ||' ' || ObjName || ' Does NOT exist!');	
    when Trigger_Expt THEN
     DBMS_OUTPUT.PUT_LINE(ObjType ||' ' || ObjName || ' Does NOT exist!');		 
    when others THEN
      DBMS_OUTPUT.PUT_LINE(sqlerrm);
end;

procedure alterTableAdd(TableName varchar2,ColName varchar2, ColType varchar2)
AS
Alter_Expt exception;
pragma exception_init(Alter_Expt, -1430);
NotExist_Expt exception;
pragma exception_init(NotExist_Expt, -942);
begin
  execute immediate 'alter table ' || TableName ||' add (' || ColName || ' ' || ColType || ')';
  DBMS_OUTPUT.PUT_LINE(TableName ||' ' || ColName || ' Added!');
  EXCEPTION
    when Alter_Expt THEN
     DBMS_OUTPUT.PUT_LINE(TableName ||' ' || ColName || ' already exists!');
	when NotExist_Expt THEN
     DBMS_OUTPUT.PUT_LINE('Table ' || TableName || ' does not exist!');
     when others THEN
      DBMS_OUTPUT.PUT_LINE(sqlerrm);
end;

procedure alterTableDrop(TableName varchar2,ColName varchar2)
AS
Alter_Expt exception;
pragma exception_init(Alter_Expt, -904);
NotExist_Expt exception;
pragma exception_init(NotExist_Expt, -942);
begin
  execute immediate 'alter table ' || TableName ||' drop column ' || ColName;
  DBMS_OUTPUT.PUT_LINE(TableName ||' ' || ColName || ' dropped!');
  EXCEPTION
    when Alter_Expt THEN
     DBMS_OUTPUT.PUT_LINE(TableName ||' ' || ColName || ' invalid identifier');
	when NotExist_Expt THEN
     DBMS_OUTPUT.PUT_LINE('Table ' || TableName || ' does not exist!');
     when others THEN
      DBMS_OUTPUT.PUT_LINE(sqlerrm);
end;

procedure alterTableRename(TableName varchar2,oldName varchar2, newName varchar2)
AS
Alter_Expt exception;
pragma exception_init(Alter_Expt, -957);
NotExist_Expt exception;
pragma exception_init(NotExist_Expt, -942);
begin
  execute immediate 'alter table ' || TableName ||' rename column ' || oldName || ' to ' || newName;
  DBMS_OUTPUT.PUT_LINE(TableName ||' ' || oldName || ' duplicate!');
  EXCEPTION
    when Alter_Expt THEN
     DBMS_OUTPUT.PUT_LINE(TableName ||' ' || oldName || ' invalid identifier');
	when NotExist_Expt THEN
     DBMS_OUTPUT.PUT_LINE('Table ' || TableName || ' does not exist!');
     when others THEN
      DBMS_OUTPUT.PUT_LINE(sqlerrm);
end;

procedure modifyTable(TableName varchar2,ColName varchar2, Command varchar2)
AS
Modify_Expt exception;
pragma exception_init(Modify_Expt, -1451);
begin
  execute immediate 'alter table ' || TableName ||' modify ' || ColName || ' ' || Command;
  DBMS_OUTPUT.PUT_LINE(TableName ||' ' || ColName || ' Modified!');
  EXCEPTION
    when Modify_Expt THEN
     DBMS_OUTPUT.PUT_LINE(TableName ||' ' || ColName || ' cannot be modified');
     when others THEN
      DBMS_OUTPUT.PUT_LINE(sqlerrm);
end;

procedure addIndex(TableName varchar2, indexName varchar2, indexColumns varchar2)
AS
Index_Expt exception;
pragma exception_init(Index_Expt, -955);
begin
  execute immediate 'CREATE INDEX ' || indexName ||' ON ' || TableName || ' (' || indexColumns || ')';
  DBMS_OUTPUT.PUT_LINE('Index ' || indexName || ' Created!');
  EXCEPTION
    when Index_Expt THEN
     DBMS_OUTPUT.PUT_LINE('Index ' || indexName || ' cannot be created');
     when others THEN
      DBMS_OUTPUT.PUT_LINE(sqlerrm);
end;

procedure addUniqueIndex(TableName varchar2, indexName varchar2, indexColumns varchar2)
AS
Index_Expt exception;
pragma exception_init(Index_Expt, -955);
begin
  execute immediate 'CREATE UNIQUE INDEX ' || indexName ||' ON ' || TableName || ' (' || indexColumns || ')';
  DBMS_OUTPUT.PUT_LINE('Index ' || indexName || ' Created!');
  EXCEPTION
    when Index_Expt THEN
     DBMS_OUTPUT.PUT_LINE('Index ' || indexName || ' name is already in use');
     when others THEN
      DBMS_OUTPUT.PUT_LINE(sqlerrm);
end;
end DB_HOUSEKEEPING;
/
