package com.eason.coding.life.jms;

import org.springframework.beans.BeansException;
import com.eason.coding.life.ApplicationContextManager;

public class ReceiveMessageMain {

	private static final String[] APPLICATION_CONTEXT_PATH = { "spring/spring-jms-template.xml" };

	public static void main(String[] args) throws BeansException, Exception {
		ApplicationContextManager.init(APPLICATION_CONTEXT_PATH);
		SimpleMessageReceiver messageReceiver = (SimpleMessageReceiver) ApplicationContextManager
				.getInstance().getBean("simpleMessageReceiver");
		messageReceiver.receiveAndPrint();
		ApplicationContextManager.destroy();
	}
}
