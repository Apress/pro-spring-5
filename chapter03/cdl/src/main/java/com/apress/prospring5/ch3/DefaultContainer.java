package com.apress.prospring5.ch3;

public class DefaultContainer implements Container {
	@Override
	public Object getDependency(String key) {
        if("myDependency".equals(key)) {
        	return new Dependency();
        }

        throw new RuntimeException("Unknown dependency: " + key);
	}
}
