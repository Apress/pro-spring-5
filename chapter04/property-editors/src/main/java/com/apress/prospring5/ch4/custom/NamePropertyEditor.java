package com.apress.prospring5.ch4.custom;

import java.beans.PropertyEditorSupport;

public class NamePropertyEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        String[] name = text.split("\\s");

        setValue(new FullName(name[0], name[1]));
    }
}
