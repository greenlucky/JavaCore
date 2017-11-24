package com.lamdevops.reflection;

import com.lamdevops.reflection.model.Cat;

/**
 * Created by lamdevops on 6/27/17.
 */
public class ShowInfoCatClass {

    public static void main(String[] args) {
        //Gets method 'Class' describe class ShowInfoClass
        Class<Cat> aClass = Cat.class;

        //Show name class consist of package name
        System.out.println("Class name: " + aClass.getName());

        //Show simple name of Class
        System.out.println("Simple name of class: " + aClass.getSimpleName());

        //Show super of this class
        System.out.println("Super of class: " + aClass.getSuperclass().getSimpleName());

        //Gets all interfaces this class implemented
        Class<?>[] itfClasses = aClass.getInterfaces();

        for (Class<?> itfClass : itfClasses) {
            System.out.println("Interface: " + itfClass.getSimpleName());
        }
    }
}
