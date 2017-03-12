package com.apress.prospring5.ch4.custom;

import org.springframework.context.support.GenericXmlApplicationContext;

public class CustomEditorExample {
    private FullName name;

    public FullName getName() {
        return name;
    }

    public void setName(FullName name) {
        this.name = name;
    }

    public static void main(String... args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/app-context-02.xml");
        ctx.refresh();

        CustomEditorExample bean =
            (CustomEditorExample) ctx.getBean("exampleBean");

        System.out.println(bean.getName());

        ctx.close();
    }
}
