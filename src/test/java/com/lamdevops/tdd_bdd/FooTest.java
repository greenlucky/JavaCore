package com.lamdevops.tdd_bdd;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class FooTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();
    private Foo foo;

    @Before
    public void inti() throws Exception {
        foo = new Foo();
    }

    @Test
    public void whenDoFooThenThrowRuntimeException() {
        exception.expect(RuntimeException.class);
    }
}