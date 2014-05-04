package com.eason.coding.life.jms;

import com.eason.coding.life.ApplicationContextManager;

public class JmsContainerMain {
    private static final String[] APPLICATION_CONTEXT_PATH = {"spring/spring-jms-container.xml"};
    public static void main(String[] args) {
    	ApplicationContextManager.init(APPLICATION_CONTEXT_PATH);
    }
}
