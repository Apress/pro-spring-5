package com.apress.prospring5.ch3.annotation;

import org.springframework.stereotype.Service;
import com.apress.prospring5.ch3.MessageProvider;

@Service("messageProvider")
public class HelloWorldMessageProvider implements MessageProvider {
    @Override
    public String getMessage() {
        return "Hello World!";
    }
}
