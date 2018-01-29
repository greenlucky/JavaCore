package com.lamdevops.annotation.validator.MatchingPassword;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FieldMatchingValidator.class)
@Documented
public @interface FieldMatching {

    String message() default "{FieldMatching.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String first();
    String second();

    @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        FieldMatching[] value();
    }
}
