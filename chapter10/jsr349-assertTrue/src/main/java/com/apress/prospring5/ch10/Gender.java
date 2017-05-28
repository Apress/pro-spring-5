package com.apress.prospring5.ch10;

public enum Gender {
    MALE("M"), FEMALE("F");

    private String code;

    private Gender(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return this.code;
    }
}
