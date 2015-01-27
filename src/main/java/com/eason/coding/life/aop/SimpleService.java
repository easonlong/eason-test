package com.eason.coding.life.aop;

import org.apache.log4j.Logger;

public class SimpleService implements Service {
	private final Logger LOGGER = Logger.getLogger(SimpleService.class);

	@Override
	public void doService(String message) {
		LOGGER.info("Processing the message:" + message);
	}

}
