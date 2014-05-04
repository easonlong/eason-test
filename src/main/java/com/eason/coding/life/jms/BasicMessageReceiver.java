package com.eason.coding.life.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.eason.coding.life.ApplicationContextManager;

public class BasicMessageReceiver {

	private static final String[] APPLICATION_CONTEXT_PATH = { "spring/spring-jms-config.xml" };
	private static final Logger LOGGER=Logger.getLogger(BasicMessageReceiver.class);
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
		// create message consumer
		MessageConsumer consumer=session.createConsumer(destination);
		connection.start();
		Message message=consumer.receive();
		if(message instanceof TextMessage){
			LOGGER.info(((TextMessage) message).getText());
		}
		connection.stop();
		consumer.close();
		session.close();
		connection.close();
		

	}

}
