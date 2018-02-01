package com.lamdevops.reflection;

import com.lamdevops.reflection.model.Cat;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by lamdevops on 6/27/17.
 */
public class AccessPrivateInfo {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        Class<Cat> catClass = Cat.class;

        // Class.getField(String) just get only if this method is public.
        // So we will use Class.getDeclareField(String):
        // Gets object Field describe field name of class Cat.
        // (This field describe in this class)
        Field privateNameField = catClass.getDeclaredField("name");


        // This method will enable flat to access private field.
        // If this flat is false to give IllegalAccessException.
        privateNameField.setAccessible(true);

        Cat tom = new Cat("tom");

        String fieldValue = (String) privateNameField.get(tom);
        System.out.println("Value field name: " + fieldValue);

        // Set new value for name field
        privateNameField.set(tom, "Tom Cat");

        System.out.println("New name: " + tom.getName());


        //get method setName(String)
        Method privateSetNameField = catClass.getDeclaredMethod("setName", String.class);

        privateSetNameField.setAccessible(true);

        privateSetNameField.invoke(tom, "Tom Ho");

        System.out.println("New name:" + tom.getName());


    }

}
