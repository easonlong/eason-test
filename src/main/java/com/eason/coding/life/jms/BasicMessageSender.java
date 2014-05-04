package com.eason.coding.life.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.springframework.context.ApplicationContext;

import com.eason.coding.life.ApplicationContextManager;

public class BasicMessageSender {
	private static final String[] APPLICATION_CONTEXT_PATH = { "spring/spring-jms-config.xml" };

	public static void main(String[] args) throws Exception {
		ApplicationContextManager.init(APPLICATION_CONTEXT_PATH);
		ApplicationContext ctx = ApplicationContextManager.getInstance();
		// get connection factory
		ConnectionFactory connFactory = (ConnectionFactory) ctx
				.getBean("defaultJmsConnectionFactory");
		// create connection
		Connection connection = connFactory.createConnection();
		// create session
		Session session = connection.createSession(false,
				Session.AUTO_ACKNOWLEDGE);
		// create destination queue
		Destination destination = session.createQueue("eason_test");
		// create message producer
		MessageProducer producer = session.createProducer(destination);
		// create message
		Message message = session.createTextMessage("hello world.");
		producer.send(message);
		producer.close();
		session.close();
		connection.close();

	}

}
