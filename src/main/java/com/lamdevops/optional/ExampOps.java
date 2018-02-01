package com.lamdevops.optional;

import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 * Created by lamdevops on 7/27/17.
 */
public class ExampOps {

    @Test
    public void testGetName() throws Exception {

        Role role = new Role();
        Role.Permission  permission = new Role.Permission("Admin");
        role.setPermission(permission);

        String name = getPermission(role);
        System.out.println(name);

    }

    public String getPermission(Role role) {
        return Optional.ofNullable(role)
                .flatMap(p -> Optional.ofNullable(role.getPermission()))
                .map(p -> p.getName()).orElse("Unknown");
    }

    public static class Role {

        private static class Permission {
            private String name;

            public Permission(String admin) {
                this.name = admin;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        private Permission permission;

        public Permission getPermission() {
            return permission;
        }

        public void setPermission(Permission permission) {
            this.permission = permission;
        }
    }
}
