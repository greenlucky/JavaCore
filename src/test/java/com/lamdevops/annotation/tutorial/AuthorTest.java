package com.lamdevops.annotation.tutorial;

import com.lamdevops.annotation.validator.composition.Car;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AuthorTest {

    private Validator validator;

    @BeforeEach
    public void inti() throws Exception{
        validator = Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(
                        new ResourceBundleMessageInterpolator(
                                new PlatformResourceBundleLocator("ValidationMessages"))
                ).buildValidatorFactory().getValidator();
    }

    @Test
    public void testNotNullAuthor() {
        Author author = new Author(null);
        System.out.println(author.toString());

        Set<ConstraintViolation<Author>> constraintViolations
                = validator.validate(author);

        constraintViolations.stream().forEach(msg -> System.out.println(msg.getMessage()));
    }

}