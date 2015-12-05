package com.eason.coding.life.classloader;

import java.lang.reflect.Method;

public class ClassLoaderTest {

	public static void main(String[] args) throws Exception {

		final MyClassLoader classLoader = new MyClassLoader();
		Thread.currentThread().setContextClassLoader(classLoader);
		System.out.println("Main class loader:" + ClassLoaderTest.class.getClassLoader());
		System.out.println("Main thread context class loader:" + Thread.currentThread().getContextClassLoader());
		Class clazz = classLoader.loadClass("com.eason.coding.life.classloader.MyThread");
		Object thread = clazz.newInstance();
		Method setClassLoader = clazz.getMethod("setClassLoader", ClassLoader.class);
		setClassLoader.invoke(thread, new Object[] { classLoader });
		Method start = clazz.getMethod("start");
		start.invoke(thread, new Object[] {});

	}

}
