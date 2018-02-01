package com.lamdevops.reflection;

import com.lamdevops.reflection.model.Cat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by lamdevops on 6/27/17.
 */
public class MethodInfo {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        // Gets object Cat class
        Class<Cat> catClass = Cat.class;

        // Gets object Method describe getAge()
        Method getAgeMethod = catClass.getMethod("getAge");

        Class<?> returnType = getAgeMethod.getReturnType();

        System.out.println("Return type of getAge: " + returnType);

        Cat tom = new Cat("Tom", 7);

        // Call method 'getAge' by Reflect type
        // That respectively with method calling tom.getAge()
        int age = (int) getAgeMethod.invoke(tom);

        System.out.println("Age: " + age);

        // Gets object Method describe method setAge(int) of class Cat.
        Method setAgeMethod = catClass.getMethod("setAge", int.class);

        // Call method setAge(int) by reflect type
        // That corresponding with calling method tom.setAge(5)
        setAgeMethod.invoke(tom, 5);

        System.out.println("New Age:" + tom.getAge());

    }

}
