package com.lamdevops.annotation.validator.MatchingPassword;


import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldMatchingValidator implements ConstraintValidator<FieldMatching, Object> {

    private String firstField;
    private String secondField;

    @Override
    public void initialize(FieldMatching constraintAnnotation) {
        this.firstField = constraintAnnotation.first();
        this.secondField = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(final Object o,
                           ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = false;
        try {
            final Object first = BeanUtils.getProperty(o, firstField);
            final Object second = BeanUtils.getProperty(o, secondField);
            isValid = (first == null && second == null) || (first != null && first.equals(second));
        } catch (Exception e) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate(e.getMessage())
                    .addConstraintViolation();
        }

        if(!isValid) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("{FieldMatching.message}")
                    .addConstraintViolation();
        }

        return isValid;
    }
}
