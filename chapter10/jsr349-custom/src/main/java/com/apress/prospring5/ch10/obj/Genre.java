package com.apress.prospring5.ch10.obj;

public enum Genre {
	POP("P"), JAZZ("J"), BLUES("B"), COUNTRY("C");
	private String code;

	private Genre(String code) {
		this.code = code;
	}

	public String toString() {
		return this.code;
	}
}
