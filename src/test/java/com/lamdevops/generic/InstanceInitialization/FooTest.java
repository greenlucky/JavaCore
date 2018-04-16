package com.lamdevops.generic.InstanceInitialization;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FooTest {

    @Test
    void withName() {
        new Foo();
        System.out.println();
        Foo foo = Foo.withName("Bar");
    }
}