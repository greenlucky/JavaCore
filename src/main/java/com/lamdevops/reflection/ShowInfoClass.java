package com.lamdevops.reflection;

import java.lang.reflect.Modifier;

/**
 * Created by lamdevops on 6/27/17.
 */
public class ShowInfoClass {

    public static void main(String[] args) {

        //Gets method 'Class' describe class ShowInfoClass
        Class<ShowInfoClass> aClass = ShowInfoClass.class;

        //Show name class consist of package name
        System.out.println("Class name: " + aClass.getName());

        //Show simple name of Class
        System.out.println("Simple name of class: " + aClass.getSimpleName());

        //Show super of this class
        System.out.println("Super of class: " + aClass.getSuperclass().getName());

        //Gets all interfaces this class implemented
        Class<?>[] itfClasses = aClass.getInterfaces();

        for (Class<?> itfClass : itfClasses) {
            System.out.println("Interface: " + itfClass.getName());
        }



        //Info of package contains this class
        Package pkg = aClass.getPackage();
        System.out.println("Package name: " + pkg.getName());

        //Modifiers
        int modifiers = aClass.getModifiers();

        boolean isPublic = Modifier.isPublic(modifiers);
        boolean isInterface = Modifier.isInterface(modifiers);
        boolean isAbstract = Modifier.isAbstract(modifiers);
        boolean isFinal = Modifier.isFinal(modifiers);

        // True
        System.out.println("Is public? " + isPublic);
        System.out.println("Is interface? " + isInterface);
        System.out.println("Is abstract? " + isAbstract);
        System.out.println("Is final? " + isFinal);





    }
}
