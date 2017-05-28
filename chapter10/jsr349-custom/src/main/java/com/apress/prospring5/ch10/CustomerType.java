package com.apress.prospring5.ch10;

public enum CustomerType {
    INDIVIDUAL("I"), CORPORATE("C");

    private String code;

    private CustomerType(String code) {
        this.code = code;
    }

    public String toString() {
        return this.code;
    }
}
