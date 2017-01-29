package com.apress.prospring5.ch3.xml;

import com.apress.prospring5.ch3.MessageProvider;

public class HelloWorldMessageProvider implements MessageProvider {
    @Override
    public String getMessage() {
        return "Hello World!";
    }
}
