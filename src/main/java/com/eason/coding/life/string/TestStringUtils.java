package com.eason.coding.life.string;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

public class TestStringUtils {
    @Test
    public void testSubstringBefore(){
        String before="CMBX-12345678-12345678";
        String after=StringUtils.substringBefore(before, "-");
        System.out.println(after);
    }
    @Test
    public void testReplaceIfNotExist(){
        String before="I am Eason.";
        String after=before.replace("Eason2", "Yaokun");
        System.out.println(after);     
             
    }
    @Test
    public void testsubstractUrl(){
    	String url="http://sdk.entinfo.cn:8060/webservice.asmx/mt";
    	String urlWithOutProtocal=url.substring(url.indexOf("//")+2);
    	System.out.println(urlWithOutProtocal);
    	String host=urlWithOutProtocal.substring(0,urlWithOutProtocal.indexOf("/"));
    	System.out.println(host);
    }
    @Test
    public void testReplace(){
    	String content="【取消訂單】尊敬的#cu_name#，您#order_time#的用車服務已取消，#Currency#根據價格說明，將從您的帳戶扣除#Currency##amount#。#global_mail#【一號專車】";
    	System.out.println(content.replace("#Currency#", "HK$"));
    }
}
