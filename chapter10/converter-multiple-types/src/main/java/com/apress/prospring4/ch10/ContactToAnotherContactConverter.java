package com.apress.prospring4.ch10;

import org.springframework.core.convert.converter.Converter;

public class ContactToAnotherContactConverter
        implements Converter<Contact, AnotherContact> {

    @Override
    public AnotherContact convert(Contact contact) {
        AnotherContact anotherContact = new AnotherContact();
        anotherContact.setFirstName(contact.getLastName());
        anotherContact.setLastName(contact.getFirstName());
        anotherContact.setBirthDate(contact.getBirthDate());
        anotherContact.setPersonalSite(contact.getPersonalSite());

        return anotherContact;
    }
}
