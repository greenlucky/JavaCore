package com.lamdevops.annotation.validator.CheckCase;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidPassengerCountValidator implements ConstraintValidator<ValidPassengerCount, Car> {
    @Override
    public void initialize(ValidPassengerCount constraintAnnotation) {
    }

    @Override
    public boolean isValid(Car object, ConstraintValidatorContext context) {

        if(object == null)
            return true;
        boolean isValid = object.getPassengers().size() <= object.getSeatCount();

        if(!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("{ValidatorPassengerCount.message}")
                    .addPropertyNode("passengers").addConstraintViolation();
        }

        return isValid;
    }
}
