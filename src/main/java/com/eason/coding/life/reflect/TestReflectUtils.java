package com.eason.coding.life.reflect;

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
			ReflectUtils.invokeMethod(eason,
					"changeNameAndBirthDate", args, true);
		} catch (Exception e) {
			LOGGER.info("Catch exception in ReflectUtils:",e);
			e.printStackTrace();
		}
		LOGGER.info(eason.introduceMyself());
	}
}
