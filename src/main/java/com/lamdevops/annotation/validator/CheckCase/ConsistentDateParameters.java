package com.lamdevops.annotation.validator.CheckCase;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = ConsistentDateParametersValidator.class)
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ConsistentDateParameters {

    String message() default "{ConsistentDateParameters.message}";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
