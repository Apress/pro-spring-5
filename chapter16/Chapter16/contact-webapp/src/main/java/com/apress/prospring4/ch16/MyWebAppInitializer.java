package com.apress.prospring4.ch16;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MyWebAppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext container) throws ServletException {
        XmlWebApplicationContext appContext = new XmlWebApplicationContext();

        appContext.setConfigLocation("/WEB-INF/spring/appServlet/servlet-context.xml");

        ServletRegistration.Dynamic dispatcher =
                container.addServlet("appServlet", new DispatcherServlet(appContext));

        MultipartConfigElement multipartConfigElement =
                new MultipartConfigElement(null, 5000000, 5000000, 0);
        dispatcher.setMultipartConfig(multipartConfigElement);

        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }
}
