package com.lamdevops.reflection;

import com.lamdevops.reflection.model.Cat;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;

/**
 * Created by lamdevops on 6/27/17.
 */
public class ShowMemberInfo {

    public static void main(String[] args) {

        //Gets class describe Cat class
        Class<Cat> catClass = Cat.class;


        // Gets constructors of Cat class
        Constructor<?>[] constructors = catClass.getConstructors();

        for (Constructor<?> constructor : constructors) {
            System.out.println("Constructor: " + constructor.getName());
        }


        // Gets all public methods
        // Consist of all methods extend from parent
        Method[] methods = catClass.getMethods();

        System.out.println("-------- METHODS ------------");
        for (Method method : methods) {
            System.out.println("Method: " + method.getName());
        }


        // Gets all field public
        // Consist of filed public extends from parent
        Field[] fields = catClass.getFields();

        System.out.println("--------- FIELDS -------------");
        for (Field field : fields) {
            System.out.println("Field: " + field.getName());
        }
    }
}
