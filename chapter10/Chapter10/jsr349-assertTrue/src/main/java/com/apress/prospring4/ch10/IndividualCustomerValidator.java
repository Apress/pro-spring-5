package com.apress.prospring4.ch10;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IndividualCustomerValidator implements
        ConstraintValidator<CheckIndividualCustomer, Customer> {

    @Override
    public void initialize(CheckIndividualCustomer constraintAnnotation) {
    }

    @Override
    public boolean isValid(Customer customer,
                           ConstraintValidatorContext context) {

        boolean result = true;

        if (customer.getCustomerType() != null &&
                (customer.isIndividualCustomer() && (customer.getLastName() == null ||
                        customer.getGender() == null))) {
            result = false;
        }

        return result;
    }
}
