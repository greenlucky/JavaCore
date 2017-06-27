package com.lamdevops.reflection;

import com.lamdevops.reflection.model.Cat;

import java.lang.reflect.Method;

/**
 * Created by lamdevops on 6/27/17.
 */
public class GetSetInfo {

    // Method is getter if has name begin get and without parameters.
    public static boolean isGetter(Method method) {

        if (!method.getName().startsWith("get"))
            return false;

        if (method.getParameterTypes().length != 0)
            return false;

        if (void.class.equals(method.getReturnType()))
            return false;

        return true;
    }

    // Method is setter if has begin set and only if only one parameter.
    public static boolean isSetter(Method method) {

        if (!method.getName().startsWith("set"))
            return false;

        if (method.getParameterTypes().length != 1)
            return false;

        return true;
    }

    public static void main(String[] args) {

        Class<Cat> catClass = Cat.class;

        Method[] methods = catClass.getMethods();

        for (Method method : methods) {
            boolean isSetter = isSetter(method);
            boolean isGetter = isGetter(method);

            System.out.println("Method: " + method.getName());
            System.out.println("- Is Setter? " + isSetter);
            System.out.println("- Is Getter? " + isGetter);

        }
    }
}
