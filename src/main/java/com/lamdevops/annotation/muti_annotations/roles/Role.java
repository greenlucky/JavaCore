package com.lamdevops.annotation.muti_annotations.roles;

import java.lang.annotation.*;

@Repeatable(Role.List.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Role {

    String value();

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        Role[] value();
    }
}
