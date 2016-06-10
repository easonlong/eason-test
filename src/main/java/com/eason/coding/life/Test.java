package com.eason.coding.life;

import java.math.BigDecimal;
import java.util.UUID;

import org.apache.commons.lang.time.StopWatch;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class Test {

	private static final Log LOGGER = LogFactory.getLog(Test.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long cost1=0;
				long cost2=0;
		StopWatch watch = new StopWatch();
		watch.start();
		for(int i=0;i<100000;i++){
			int a = 1+1;
		}
		watch.stop();
		cost1=watch.getTime();
		watch.reset();
		watch.start();
		for(int i=0;i<100000;i++){
			int a = 1+1;
			LOGGER.info("sdfsdfsdfsdfsgdfgligjlgj");
		}
		watch.stop();
		cost2=watch.getTime();
		System.out.println(cost1);
		System.out.println(cost2);
	    
    }

	public static void test1() {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			double a = 3.14 * 3.14;
		}

		for (int j = 0; j < 1000000; j++) {
			double b = 6.45 * 3.14;
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}

	public static void test2() {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			double a = 3.14 * 3.14;
			double b = 6.45 * 3.14;
		}

		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}
	
	public static void test3(){
		String string="abcd";
		
		System.out.println(string.substring(0,string.indexOf("=")));
		System.out.println(string.substring(string.indexOf("=")+1));
	}
	
}
