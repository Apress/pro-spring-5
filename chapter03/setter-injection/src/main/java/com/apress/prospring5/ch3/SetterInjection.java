package com.apress.prospring5.ch3;

public class SetterInjection {

	private Dependency dependency;

	public void setDependency(Dependency dependency) {
		this.dependency = dependency;
	}

	@Override
	public String toString() {
		return dependency.toString();
	}
}
