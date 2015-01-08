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
}
