package com.apress.prospring5.ch3;

public class CDLDemo {
    public static void main(String... args) {
    	Container container = new DefaultContainer();

    	ManagedComponent managedComponent = new ContextualizedDependencyLookup();
    	managedComponent.performLookup(container);

    	System.out.println(managedComponent);
    }
}
