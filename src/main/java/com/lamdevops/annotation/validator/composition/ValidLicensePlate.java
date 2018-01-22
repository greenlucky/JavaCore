package com.lamdevops.annotation.validator.composition;

import com.lamdevops.annotation.validator.CheckCase.CaseMode;
import com.lamdevops.annotation.validator.CheckCase.CheckCase;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.annotation.*;

@NotNull
@Size(min = 2, max = 14)
@CheckCase(CaseMode.UPPER)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
public @interface ValidLicensePlate {

    String message() default "{InvalidLicensePlate.message}";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
