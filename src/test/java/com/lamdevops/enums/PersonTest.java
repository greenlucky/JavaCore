package com.lamdevops.enums;

import org.junit.jupiter.api.Test;


class PersonTest {

    @Test
    public void testPerson() {
        Person.JOE.speak();
    }

    @Test
    public void testPerson1() {
        Person1.JOE.speak();
        Person1.JIM.speak();
    }
}