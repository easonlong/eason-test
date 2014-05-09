package com.eason.coding.life.regex;

import java.util.regex.Pattern;

public class RegexTest {

	public static void main(String[] args) {
		try {
		Pattern pattern=Pattern.compile(".*[*].*");
		String str="EUR_EURIBOR_ACT360_3MT";
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
