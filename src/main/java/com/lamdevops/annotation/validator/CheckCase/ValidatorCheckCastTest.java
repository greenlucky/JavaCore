package com.lamdevops.annotation.validator.CheckCase;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidatorCheckCastTest {
    private Validator validator;
    @BeforeEach
    public void init() throws Exception {
        validator = Validation.byDefaultProvider().configure()
                .messageInterpolator(
                        new ResourceBundleMessageInterpolator(
                                new PlatformResourceBundleLocator("ValidationMessages")
                        )
                ).buildValidatorFactory().getValidator();
    }

    @Test
    public void testValidatorCar() throws Exception {

        Car car = new Car(null, "dd-ab-123", 4);


        Set<ConstraintViolation<Car>> constraintViolations = validator.validate(car);
        assertEquals(2, constraintViolations.size());
        /*assertEquals("Case mode must be UPPER",
                constraintViolations.iterator().next().getMessage());*/
        constraintViolations.stream().forEach(msg -> System.out.println(msg.getMessage()));

        //valid license plate
        car = new Car("Morris", "DD-AA-123", 4);

        constraintViolations = validator.validate(car);

        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void testValidatorCarWithCountPassenger() throws Exception {

        List<String> passengers = Arrays.asList("Mary", "Peter", "Jobs", "Hanrry", "Steve");

        Car car = new Car("Morris", "DD-AB-123", 4, passengers);

        Set<ConstraintViolation<Car>> constraintViolations = validator.validate(car);
        assertEquals(1, constraintViolations.size());
        constraintViolations.stream().forEach(msg -> System.out.println(msg.getMessage()));
    }

    @Test
    public void testValidatorCarWithScriptAssert() throws Exception {

        List<String> passengers = Arrays.asList("Mary", "Peter", "Jobs", "Hanrry", "Steve");

        Car car = new Car();
        car.buildCar(4, passengers);

        Set<ConstraintViolation<Car>> constraintViolations = validator.validate(car);
        assertEquals(1, constraintViolations.size());
        constraintViolations.stream().forEach(msg -> System.out.println(msg.getMessage()));

    }
}
