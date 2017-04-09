package com.apress.prospring5.ch5;

import com.apress.prospring5.ch2.common.Guitar;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

public class NamePointcutDemo {

	public static void main(String... args) {
		GrammyGuitarist johnMayer = new GrammyGuitarist();

		NameMatchMethodPointcut pc = new NameMatchMethodPointcut();
		pc.addMethodName("sing");
		pc.addMethodName("rest");

		Advisor advisor = new DefaultPointcutAdvisor(pc, new SimpleAdvice());
		ProxyFactory pf = new ProxyFactory();
		pf.setTarget(johnMayer);
		pf.addAdvisor(advisor);

		GrammyGuitarist proxy = (GrammyGuitarist) pf.getProxy();
		proxy.sing();
		proxy.sing(new Guitar());
		proxy.rest();
		proxy.talk();
	}
}
