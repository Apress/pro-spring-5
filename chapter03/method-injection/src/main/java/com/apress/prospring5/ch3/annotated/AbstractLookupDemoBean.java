package com.apress.prospring5.ch3.annotated;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component("abstractLookupBean")
public class AbstractLookupDemoBean implements DemoBean {
    @Lookup("singer")
    public Singer getMySinger() {
        return null; // This implementation will be overridden by dynamically generated subclass
    }

    @Override
    public void doSomething() {
        getMySinger().sing();
    }
}