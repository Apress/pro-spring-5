package com.apress.prospring5.ch5;

import org.springframework.aop.framework.ProxyFactory;

public class SingerAOPDemo {
    public static void main(String[] args) {
        Singer target = new Singer();

        ProxyFactory pf = new ProxyFactory();
        pf.addAdvice(new SingerDecorator());
        pf.setTarget(target);

        Singer proxy = (Singer) pf.getProxy();

        target.sing();
        System.out.println("");
        proxy.sing();
    }
}
