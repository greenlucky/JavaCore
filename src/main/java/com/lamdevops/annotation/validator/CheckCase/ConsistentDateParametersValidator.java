package com.lamdevops.annotation.validator.CheckCase;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;
import java.time.LocalDate;
import java.util.Date;

@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class ConsistentDateParametersValidator implements
        ConstraintValidator<ConsistentDateParameters, Object[]> {

    @Override
    public void initialize(ConsistentDateParameters constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object[] objects, ConstraintValidatorContext context) {

        if (objects.length != 2) {
            throw new IllegalArgumentException("Illegal method signature");
        }

        //leave null-checking to @NotNull on individual parameters
        if (objects[0] == null || objects[1] == null)
            return true;

        if(!(objects[0] instanceof LocalDate) || !(objects[1] instanceof LocalDate)) {
            throw new IllegalArgumentException(
              "Illegal method signature, expected two " +
                      "parameters of type Date.");
        }

        return ((LocalDate) objects[0]).isBefore((LocalDate) objects[1]);
    }
}
