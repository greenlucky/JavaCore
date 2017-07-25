package com.lamdevops.lambda.models;

/**
 * Created by lam.nm on 7/17/2017.
 */
public interface Idenfified {
    default int getId() { return Math.abs(hashCode());}
}
