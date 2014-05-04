package com.eason.coding.life.timer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SimpleTimerTaskMain {

    private static final String APPLICATION_CONTEXT_PATH = "spring/spring-simple-timer.xml";
    public static void main(String[] args) {
	ApplicationContext ctx= new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_PATH);
	while(true){
	    try {
		System.out.println("Main Thread sleep 2s");
	        Thread.sleep(2000);
            } catch (InterruptedException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
            }
	}
    }

}
