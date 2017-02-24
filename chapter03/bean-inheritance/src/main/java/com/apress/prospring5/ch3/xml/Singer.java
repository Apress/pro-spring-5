package com.apress.prospring5.ch3.xml;

/**
 * Created by iuliana.cosmina on 2/25/17.
 */
public class Singer {
    private String name;
    private int age;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
        
    public String toString() {
        return "\tName: " + name + "\n\t" + "Age: " + age;
    }
}
