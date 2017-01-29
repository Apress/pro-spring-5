package com.apress.prospring4.ch6;

import java.io.Serializable;

public class ContactTelDetail implements Serializable {
    private Long id;
    private Long contactId;
    private String telType;
    private String telNumber;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public Long getContactId() {
        return this.contactId;
    }

    public void setTelType(String telType) {
        this.telType = telType;
    }

    public String getTelType() {
        return this.telType;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getTelNumber() {
        return this.telNumber;
    }

    public String toString() {
        return "Contact Tel Detail - Id: " + id + ", Contact id: " + contactId
            + ", Type: " + telType + ", Number: " + telNumber;
    }
}
