package com.apress.prospring5.ch12.entities;

import com.apress.prospring5.ch12.entities.Singer;

import java.io.Serializable;
import java.util.List;

public class Singers implements Serializable {
    private List<Singer> contacts;

    public Singers() {
    }

    public Singers(List<Singer> contacts) {
        this.contacts = contacts;
    }

    public List<Singer> getSingers() {
        return contacts;
    }

    public void setSingers(List<Singer> contacts) {
        this.contacts = contacts;
    }
}
