package com.eason.coding.life.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.log4j.Logger;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.SessionAwareMessageListener;

public class JmsServiceDispatcher extends DefaultMessageListenerContainer implements SessionAwareMessageListener{
	private static final Logger LOGGER = Logger
			.getLogger(JmsServiceDispatcher.class);
	@Override
	public void onMessage(Message message, Session session)
			throws JMSException {
		LOGGER.info(message.toString());
		LOGGER.info(Thread.currentThread().getName()
				+ " will sleep 10 seconds.");
		for(int i=0; i<10; i++){
			LOGGER.info(Thread.currentThread().getName()+" "+i);
		}
		LOGGER.info(Thread.currentThread().getName()+" is done");
		
	}
	
}
