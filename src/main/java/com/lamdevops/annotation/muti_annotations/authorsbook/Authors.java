package com.lamdevops.annotation.muti_annotations.authorsbook;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by lamdevops on 8/6/17.
 */
@Retention(RetentionPolicy.RUNTIME)
@interface Authors {
    Author[] value();
}
