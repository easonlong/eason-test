package com.eason.coding.life.string;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

public class TestStringUtils {
    @Test
    public void testSubstringBefore(){
        String before="CMBX-123456-123456";
        String after=StringUtils.substringBefore(before, "-");
        System.out.println(after);
    }
}
