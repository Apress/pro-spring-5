package com.apress.prospring5.ch5;

import org.aspectj.lang.JoinPoint;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class AuditAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target)
            throws Throwable {
        System.out.println("Executing: " + method);
    }
}
