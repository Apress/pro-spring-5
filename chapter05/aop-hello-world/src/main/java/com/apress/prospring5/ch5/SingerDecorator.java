package com.apress.prospring5.ch5;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class SingerDecorator implements MethodInterceptor {
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.print("I'd like to get to know you /");

        Object retVal = invocation.proceed();

        System.out.println(" /Cause I still feel like your man");
        return retVal;
    }
}
