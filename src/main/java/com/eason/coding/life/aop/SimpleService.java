package com.eason.coding.life.aop;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component("service")
public class SimpleService implements Service {
	private final Logger LOGGER = Logger.getLogger(SimpleService.class);

	private String name;
	
	public SimpleService(){
		LOGGER.info("default constructor");
	}
	
	public SimpleService(String name){
		LOGGER.info("constructor with arg");
		this.name = name;
	}
	@Override
	public void doService(String message) {
		LOGGER.info(this.name + " is processing the message:" + message);
	}

}
