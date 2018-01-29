package com.lamdevops.annotation.validator.CheckCase;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckCaseValidator implements ConstraintValidator <CheckCase, String> {

    private CaseMode caseMode;

    @Override
    public void initialize(CheckCase constraintAnnotation) {
        this.caseMode = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String object, ConstraintValidatorContext context) {

        if(object == null)
            return true;

        boolean isValid;
        if(caseMode == CaseMode.UPPER)
            isValid = object.equals(object.toUpperCase());
        else
            isValid = object.equals(object.toLowerCase());

        if(!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    "{CheckCase.message}"
            ).addConstraintViolation();
        }

        return isValid;
    }
}
