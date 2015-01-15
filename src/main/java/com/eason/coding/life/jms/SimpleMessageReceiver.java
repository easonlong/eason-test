package com.eason.coding.life.jms;

import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;

public class SimpleMessageReceiver {
	private static final Logger LOGGER = Logger
			.getLogger(SimpleMessageReceiver.class);
	private JmsTemplate jmsTemplate;
	private String queueName;

	public void receiveAndPrint() {
		Object msg = jmsTemplate.receiveAndConvert();

		if (msg instanceof String) {

			LOGGER.info("Received: " + msg);

		}
	}

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

}
