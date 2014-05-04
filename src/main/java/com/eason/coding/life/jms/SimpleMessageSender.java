package com.eason.coding.life.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class SimpleMessageSender {
    private JmsTemplate jmsTemplate;
    private String queueName;
    public void sendMessage(final String message) {
	MessageCreator msg = new MessageCreator() {

	    public Message createMessage(Session session) throws JMSException {

		TextMessage msg = session.createTextMessage(message);

		return msg;

	    }

	};
	getJmsTemplate().send(msg);
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
