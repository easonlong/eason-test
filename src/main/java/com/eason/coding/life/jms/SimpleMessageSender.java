package com.eason.coding.life.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.BeansException;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.eason.coding.life.ApplicationContextManager;

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

	public void sendByMutipleSenders()
			throws BeansException, Exception {
		TaskExecutor taskExecutor = (TaskExecutor) ApplicationContextManager
				.getInstance().getBean("defaultTaskExecutor");
		for (int i = 0; i < 5; i++) {
			taskExecutor.execute(new SenderTask());
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

	class SenderTask implements Runnable {

		@Override
		public void run() {
			SimpleMessageSender messageSender;
			try {
				messageSender = (SimpleMessageSender) ApplicationContextManager
						.getInstance().getBean("simpleMessageSender");
				for (int i = 0; i < 20; i++) {
					messageSender.sendMessage("hello eason!" + i);
				}
			} catch (BeansException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// TODO Auto-generated method stub

		}

	}

}
