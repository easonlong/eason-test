package com.eason.coding.life;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextManager implements ApplicationContextAware{
	private static final Logger LOGGER=Logger.getLogger(ApplicationContextManager.class);
	private static ApplicationContext context;
	private static boolean initialized = false;

	public static void init(String[] contextPath) {
		if (!initialized) {
			context = new ClassPathXmlApplicationContext(contextPath);
			initialized=true;
		}
	}

	public static ApplicationContext getInstance() throws Exception {
		if (context == null) {
			throw new Exception("context hasn't been initialized.");
		}
		return context;
	}
	public static void destroy(){
		if(context!=null){
			LOGGER.info("destroy the contex...");
			context=null;
			System.exit(1);
		}
	}
	public static Object getBean(String name) throws Exception{
		return getInstance().getBean(name);
	}
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.context = applicationContext;
	}
}
