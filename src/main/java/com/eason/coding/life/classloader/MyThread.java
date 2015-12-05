package com.eason.coding.life.classloader;

import java.util.Calendar;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.eason.coding.life.entity.Person;

public class MyThread extends Thread {
	private ClassLoader classLoader;

	@Override
	public void run() {
		Class<Person> clazz;
		try {
			System.out.println("MyThread class loader:" + MyThread.class.getClassLoader());
			System.out.println("t1 thread context class loader:" + Thread.currentThread().getContextClassLoader());
			clazz = (Class<Person>) classLoader.loadClass("com.eason.coding.life.entity.Person");
			Person person = clazz.newInstance();
			person.setName("Eason");
			person.setBirthDate(Calendar.getInstance());
			System.out.println(ToStringBuilder.reflectionToString(person));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void setClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}
}
