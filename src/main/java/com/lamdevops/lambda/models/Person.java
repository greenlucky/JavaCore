package com.lamdevops.lambda.models;

/**
 * Created by lam.nm on 7/17/2017.
 */
public interface Person {
    String getName();
    default int getId() {return 0;}
}
