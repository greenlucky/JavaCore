package com.lamdevops.annotation.validator.composition;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class ValidLicensePlateCompositionTest {

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
    public void testInvalidLicensePlateCar() throws Exception {
        Car car = new Car("Mirros", "aa-u12-02", 4);

        Set<ConstraintViolation<Car>> constraintViolations
                = validator.validate(car);

        constraintViolations.stream().forEach(msg -> System.out.println(msg.getMessage()));
    }

    @Test
    public void testValidLicensePlateCar() throws Exception {
        Car car = new Car("Mirros", "AA-U12-02", 4);

        Set<ConstraintViolation<Car>> constraintViolations
                = validator.validate(car);

        constraintViolations.stream().forEach(msg -> System.out.println(msg.getMessage()));
    }
}
