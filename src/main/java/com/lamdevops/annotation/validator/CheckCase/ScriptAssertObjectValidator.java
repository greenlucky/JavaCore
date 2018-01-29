package com.lamdevops.annotation.validator.CheckCase;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ScriptAssertObjectValidator implements ConstraintValidator<ScriptAssert, Object> {

    @Override
    public void initialize(ScriptAssert constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }
}
