package com.lamdevops.annotation.muti_annotations.roles;


import org.junit.jupiter.api.Test;


class AccountResourceTest {

    @Test
    public void testRepeatAnnotation() {
        Role[] roles = AccountResource.class.getAnnotationsByType(Role.class);
        java.util.Arrays.asList(roles).stream().forEach(an -> System.out.println(an.value()));
    }

}