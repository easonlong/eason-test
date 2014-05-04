package com.eason.coding.life.jms;

import org.springframework.beans.BeansException;
import com.eason.coding.life.ApplicationContextManager;
import com.eason.coding.life.jms.SimpleMessageSender;
public class SendMessageMain {

	private static final String[] APPLICATION_CONTEXT_PATH = { "spring/spring-jms-template.xml" };

	public static void main(String[] args) throws BeansException, Exception {
		ApplicationContextManager.init(APPLICATION_CONTEXT_PATH);
		SimpleMessageSender messageSender = (SimpleMessageSender) ApplicationContextManager
				.getInstance().getBean("simpleMessageSender");
		for (int i = 0; i < 100; i++) {
			messageSender.sendMessage("hello eason!" + i);
		}
		ApplicationContextManager.destroy();
	}

}
