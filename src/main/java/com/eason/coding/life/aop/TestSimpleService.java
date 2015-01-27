package com.eason.coding.life.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSimpleService {
	private static final String[] APPLICATION_CONTEXT_PATH = { "spring/spring-aop-aspectj.xml" };

	public static void main(String[] args) throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				APPLICATION_CONTEXT_PATH);
		Service service = (Service) ac.getBean("service");
		service.doService("Hello, World!");
	}
}
