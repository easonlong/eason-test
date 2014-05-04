package com.eason.coding.life.basic;

public class ClassResearch {
	public void testClassForName() throws Exception {
		Class clazz=Class.forName("java.lang.String");
		clazz.newInstance();
	}
}
