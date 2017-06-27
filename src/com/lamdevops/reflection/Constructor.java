package com.lamdevops.reflection;

import com.lamdevops.reflection.model.Cat;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by lamdevops on 6/27/17.
 */
public class Constructor {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Class<Cat> catClass = Cat.class;

        // Gets constructor has parameter (String, int) of Cat class
        java.lang.reflect.Constructor<?> constructor = catClass.getConstructor(String.class, int.class);

        // Gets info parameter sentence type
        Class<?>[] paramClasses = constructor.getParameterTypes();

        for (Class<?> paramClass : paramClasses) {
            System.out.println("Param: " + paramClass.getSimpleName());
        }

        //Construct Cat normal way
        Cat tom = new Cat("Tom", 3);
        System.out.println("Cat: " + tom.getName() + "; age: " + tom.getAge());

        // Construct reflect way
        Cat tom2 = (Cat) constructor.newInstance("Tom", 2);

        System.out.println("Cat: " + tom2.getName() + "; age: " + tom2.getAge());

        // Gets constructor has a parameter is of Cat class
        java.lang.reflect.Constructor<?> constructor1 = catClass.getConstructor(String.class);
        for (Class<?> paramClass: constructor1.getParameterTypes()) {
            System.out.println("Param:" + paramClass.getSimpleName());
        }
    }
}