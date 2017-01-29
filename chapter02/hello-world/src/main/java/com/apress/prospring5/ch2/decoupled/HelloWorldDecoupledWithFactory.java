package com.apress.prospring5.ch2.decoupled;

public class HelloWorldDecoupledWithFactory {

	public static void main(String... args) {
		MessageRenderer mr = MessageSupportFactory.getInstance().getMessageRenderer();
		MessageProvider mp = MessageSupportFactory.getInstance().getMessageProvider();
		mr.setMessageProvider(mp);
		mr.render();
	}
}
