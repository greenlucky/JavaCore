package com.lamdevops.annotation.muti_annotations;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by lamdevops on 8/6/17.
 */
@Repeatable(Authors.class)
@Retention(RetentionPolicy.RUNTIME)
@interface Author {
    String name();
}
