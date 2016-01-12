package com.eason.coding.life.regex;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class RegexTest {

	public static void main(String[] args) {
		try {
		Pattern pattern=Pattern.compile("[//s//S]*");
		//String str=";\n1;2;3;4\n5";
		String str="V";
		if(pattern.matcher(str) .matches()){
			System.out.println("match");
		}else{
			System.out.println("not match");
		}
		}catch(Throwable e){
			e.printStackTrace();
		}

	}
	
	@Test
	public void testGroup() {
		Pattern pattern = Pattern.compile("^\\$\\{(.*)\\.(.*)\\}");
		Matcher matcher = pattern.matcher("${abc.cba}");
		if (matcher.find()) {
			System.out.println(matcher.group(2));
		}

	}

}
