package com.lamdevops.annotation.validator.CheckCase;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ValidPassengerCountValidator.class})
@Documented
public @interface ValidPassengerCount {

    String message() default "{ValidatorPassengerCount.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
