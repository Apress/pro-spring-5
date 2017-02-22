package com.apress.prospring5.ch3.sandbox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by iuliana.cosmina on 2/23/17.
 */
@Component("gigi")
@Lazy
public class TrickyTarget {

	Foo foo;

	Foo foo2;
	Bar bar;

	public TrickyTarget() {
		System.out.println("Target.constructor()");
	}

	public TrickyTarget(Foo foo) {
		System.out.println("Target(Foo) called");
	}

	public TrickyTarget(Foo foo, Bar bar) {
		System.out.println("Target(Foo, Bar) called");
	}

	// comment @Qualifier annotation to cause NoUniqueBeanDefinitionException being thrown at runtime
	@Autowired
	@Qualifier("fooImpl1")
	public void setFoo(Foo foo) {
		this.foo = foo;
		System.out.println("Property foo set");
	}

	// comment @Qualifier annotation to cause NoUniqueBeanDefinitionException being thrown at runtime
	@Autowired
	@Qualifier("fooImpl2")
	public void setFoo2(Foo foo) {
		this.foo2 = foo;
		System.out.println("Property foo2 set");
	}

	@Autowired
	public void setBar(Bar bar) {
		this.bar = bar;
		System.out.println("Property bar set");
	}

	public static void main(String... args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:spring/app-context-04.xml");
		ctx.refresh();
		TrickyTarget t = ctx.getBean(TrickyTarget.class);
		ctx.close();
	}
}
