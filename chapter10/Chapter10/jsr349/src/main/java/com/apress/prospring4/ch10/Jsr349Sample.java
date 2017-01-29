package com.apress.prospring4.ch10;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Jsr349Sample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/jsr349-app-context.xml");
        ctx.refresh();

        MyBeanValidationService myBeanValidationService =
                ctx.getBean("myBeanValidationService", MyBeanValidationService.class);

        Customer customer = new Customer();
        customer.setFirstName("C");
        customer.setLastName("Schaefer");
        customer.setCustomerType(null);
        customer.setGender(null);

        validateCustomer(customer, myBeanValidationService);
    }

    private static void validateCustomer(Customer customer,
                                         MyBeanValidationService myBeanValidationService) {

        Set<ConstraintViolation<Customer>> violations =
                new HashSet<ConstraintViolation<Customer>>();
        violations = myBeanValidationService.validateCustomer(customer);

        listViolations(violations);
    }

    private static void listViolations(Set<ConstraintViolation<Customer>> violations) {
        System.out.println("No. of violations: " + violations.size());

        for (ConstraintViolation<Customer> violation: violations) {
            System.out.println("Validation error for property: " +
                    violation.getPropertyPath()
                    + " with value: " + violation.getInvalidValue()
                    + " with error message: " + violation.getMessage());
        }
    }
}
