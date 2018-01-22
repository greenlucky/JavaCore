package com.lamdevops.annotation.validator.CheckCase;

import javax.validation.Constraint;
import javax.validation.ConstraintTarget;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = {ScriptAssertObjectValidator.class, ScriptAssertParametersValidator.class})
@Target({ElementType.TYPE, ElementType.FIELD,
        ElementType.PARAMETER, ElementType.METHOD,
        ElementType.CONSTRUCTOR, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ScriptAssert {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String script();

    ConstraintTarget validationAppliesTo() default ConstraintTarget.IMPLICIT;
}
