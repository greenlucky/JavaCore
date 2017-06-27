package com.lamdevops.reflection;

import java.lang.reflect.Method;

/**
 * Created by lamdevops on 6/27/17.
 */
public class ListMethod {

    protected void info() {

    }

    public static void testMethodA() {

    }

    public static void testMethodB () {

    }

    public static void main(String[] args) {

        //Gets all public method this class consist of all method extends from parents
        Method[] methods = ListMethod.class.getMethods();

        for (Method method : methods) {
            System.out.println("Method " + method.getName());
        }

    }
}
