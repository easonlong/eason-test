package com.eason.coding.life.regex;

import java.util.regex.Pattern;

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

}
