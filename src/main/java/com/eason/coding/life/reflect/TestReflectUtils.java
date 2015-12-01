package com.eason.coding.life.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.eason.coding.life.entity.Person;

public class TestReflectUtils {
	private static final Logger LOGGER = Logger
			.getLogger(TestReflectUtils.class);

	@Test
	public void testInvokePrivateMethod() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(1990, 1, 21);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.set(1991, 2, 22);
		Person eason = new Person("Eason", calendar);
		LOGGER.info(eason.introduceMyself());
		Object[] args = new Object[2];
		args[0] = "Eason2";
		args[1] = calendar2;
		try {
			ReflectUtils.invokeMethod(eason, "changeNameAndBirthDate", args,
					true);
		} catch (Exception e) {
			LOGGER.info("Catch exception in ReflectUtils:", e);
			e.printStackTrace();
		}
		LOGGER.info(eason.introduceMyself());
	}

	@Test
	public void test() throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, InstantiationException, NoSuchFieldException {
		Class<?> clazz = Pojo.class;
		Pojo obj = (Pojo) clazz.newInstance();
		Field field=clazz.getField("id");
		Method getter = clazz.getMethod("getId", null);
		Class<?> fieldType = getter.getReturnType();
		Method setter = clazz.getMethod("setId", fieldType);
		setter.invoke(obj, this.value("78", fieldType));
		System.out.println(obj.getId());
	}
	
	private Object value(String value, Class<?> type){
		if(type.isAssignableFrom(String.class)){
			return String.valueOf(value);
		}else if(type.isAssignableFrom(Long.class) || type.isAssignableFrom(long.class)){
			return Long.valueOf(value);
		}else {
			return null;
		}
	}
}
