package com.eason.coding.life.jms;

import com.eason.coding.life.ApplicationContextManager;
import com.eason.coding.life.jms.SimpleMessageSender;
public class SendMessageMain {

	private static final String[] APPLICATION_CONTEXT_PATH = { "spring/spring-jms-template.xml","spring/spring-concurrent-task-executor.xml" };

	public static void main(String[] args) {
		try{
		ApplicationContextManager.init(APPLICATION_CONTEXT_PATH);
		SimpleMessageSender messageSender = (SimpleMessageSender) ApplicationContextManager
				.getInstance().getBean("simpleMessageSender");
		messageSender.sendByMutipleSenders();
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
