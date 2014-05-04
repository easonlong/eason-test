package com.eason.coding.life.jms;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class SimpleJmsTemplate extends JmsTemplate {
    private String destinationName;
    private MessageCreator messageCreator;
    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public MessageCreator getMessageCreator() {
        return messageCreator;
    }

    public void setMessageCreator(MessageCreator messageCreator) {
        this.messageCreator = messageCreator;
    }
    
}
