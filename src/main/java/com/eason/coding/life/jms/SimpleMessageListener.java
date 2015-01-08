package com.eason.coding.life.jms;

import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.log4j.Logger;

public class SimpleMessageListener implements MessageListener {
	private static final Logger LOGGER = Logger
			.getLogger(SimpleMessageListener.class);
	private int involkCount=0;
	@Override
	public void onMessage(Message message) {
		//involkCount=0;
		LOGGER.info(message.toString());
		LOGGER.info(Thread.currentThread().getName()
				+ " involk count is "+ ++involkCount);
		for(int i=0; i<30; i++){
			LOGGER.info(Thread.currentThread().getName()+" "+i);
		}
		LOGGER.info(Thread.currentThread().getName()+" is done");

	}

}
