package com.lamdevops.annotation.validator.MatchingPassword;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

class PasswordTest {

    private Validator validator;

    @BeforeEach
    public void init() throws Exception {
        validator = Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(
                        new ResourceBundleMessageInterpolator(
                                new PlatformResourceBundleLocator("ValidationMessages")
                        )
                ).buildValidatorFactory().getValidator();
    }

    @Test
    public void testMatchingPassword() throws Exception {
        Password password = new Password("123456", "123456");

        Set<ConstraintViolation<Password>> constraintViolations =
                validator.validate(password);

        constraintViolations.stream().forEach(msg -> System.out.println(msg.getMessage()));


    }

    @Test
    public void testNotMatchingPassword() throws Exception {
        Password password = new Password("123456", "123456");

        Set<ConstraintViolation<Password>> constraintViolations =
                validator.validate(password);

        constraintViolations.stream().forEach(msg -> System.out.println(msg.getMessage()));
    }

    @Test
    public void testScriptAssertMatchingPassword() throws Exception {
        PasswordScriptAssert password = new PasswordScriptAssert("123456", "123456");

        Set<ConstraintViolation<PasswordScriptAssert>> constraintViolations =
                validator.validate(password);

        constraintViolations.stream().forEach(msg -> System.out.println(msg.getMessage()));
    }

    @Test
    public void testScriptAssertNotMatchingPassword() throws Exception {
        PasswordScriptAssert password = new PasswordScriptAssert("123456", "1234567");

        Set<ConstraintViolation<PasswordScriptAssert>> constraintViolations =
                validator.validate(password);

        constraintViolations.stream().forEach(msg -> System.out.println(msg.getMessage()));
    }

}