package com.eason.coding.life.jetty;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JettyServer {
	private static final String[] APPLICATION_CONTEXT_PATH = { "spring/spring-jetty-server.xml" };
	 public static void main(String[] args) throws Exception {  
	        new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_PATH);  
	    }  
}
