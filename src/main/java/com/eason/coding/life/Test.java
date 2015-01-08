package com.eason.coding.life;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
		test2();

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

}
