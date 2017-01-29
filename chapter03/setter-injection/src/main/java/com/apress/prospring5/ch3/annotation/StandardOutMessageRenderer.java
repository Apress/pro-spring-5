package com.apress.prospring5.ch3.annotation;

import org.springframework.stereotype.Service;
import com.apress.prospring5.ch3.MessageRenderer;
import com.apress.prospring5.ch3.MessageProvider;
import org.springframework.beans.factory.annotation.Autowired;

@Service("messageRenderer")
public class StandardOutMessageRenderer implements MessageRenderer {
    private MessageProvider messageProvider;

    @Override
    public void render() {
        if (messageProvider == null) {
            throw new RuntimeException(
            "You must set the property messageProvider of class:"
            + StandardOutMessageRenderer.class.getName());
        }

        System.out.println(messageProvider.getMessage());
    }

    @Override
    @Autowired
    public void setMessageProvider(MessageProvider provider) {
        this.messageProvider = provider;
    }

    @Override
    public MessageProvider getMessageProvider() {
        return this.messageProvider;
    }
}
