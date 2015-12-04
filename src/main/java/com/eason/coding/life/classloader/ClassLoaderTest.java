package com.eason.coding.life.classloader;

import java.lang.reflect.Method;
import java.util.Calendar;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.eason.coding.life.entity.Person;

public class ClassLoaderTest {

	public static void main(String[] args) throws Exception {
		MyClassLoader classLoader = new MyClassLoader();
		Class<Person> clazz = (Class<Person>) classLoader.loadClass("com.eason.coding.life.entity.Person");
		Object person = clazz.newInstance();
		Method setName = clazz.getMethod("setName", String.class);
		Method setBirthDate = clazz.getMethod("setBirthDate", Calendar.class);
		setName.invoke(person, new Object[] { "Eason" });
		setBirthDate.invoke(person, new Object[] { Calendar.getInstance() });
		System.out.println(ToStringBuilder.reflectionToString(person));
	}
}
