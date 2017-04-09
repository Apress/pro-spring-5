package com.apress.prospring5.ch5;

import org.aspectj.lang.JoinPoint;

/**
 * Created by iuliana.cosmina on 4/9/17.
 */
public class SimpleAdvice {

	public void simpleBeforeAdvice(JoinPoint joinPoint) {
		System.out.println("Executing: " +
				joinPoint.getSignature().getDeclaringTypeName() + " "
				+ joinPoint.getSignature().getName());
	}
}

