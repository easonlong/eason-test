package com.eason.coding.life.jms;

import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.log4j.Logger;

public class SimpleMessageListener implements MessageListener {
	private static final Logger LOGGER = Logger
			.getLogger(SimpleMessageListener.class);

	@Override
	public void onMessage(Message message) {
		LOGGER.info(message.toString());
		LOGGER.info(Thread.currentThread().getName()
				+ " will sleep 10 seconds.");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			LOGGER.info("error:", e);
			e.printStackTrace();
		}

	}

}
