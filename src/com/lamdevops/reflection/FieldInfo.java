package com.lamdevops.reflection;

import com.lamdevops.reflection.model.Cat;

import java.lang.reflect.Field;

/**
 * Created by lamdevops on 6/27/17.
 */
public class FieldInfo {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        Class<Cat> catClass = Cat.class;

        // Gets a field has name's NUMBER_OF_LEGS;
        Field field = catClass.getField("NUMBER_OF_LEGS");

        System.out.println("Field NUMBER_OF_LEGS: " + field);

        // GET type of field
        Class<?> fieldType  = field.getType();

        System.out.println("Field type: " + fieldType.getSimpleName());

        //Gets field has name's age;

        Field ageField = catClass.getField("age");

        Cat tom = new Cat("Tom", 5);

        // Gets value of field 'age' by reflect way
        Integer age = (Integer) ageField.get(tom);

        System.out.println("Age: " + age);

        //Sets new value to field age
        ageField.set(tom, 7);

        System.out.println("New age: " + tom.getAge());
    }

}
