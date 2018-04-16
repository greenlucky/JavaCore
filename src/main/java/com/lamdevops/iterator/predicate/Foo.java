package com.lamdevops.iterator.predicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import static java.util.Arrays.asList;

public class Foo {

    private final int value;


    public Foo(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Foo{" +
                "value=" + value +
                '}';
    }

    public static void main(String[] args) {
        List<Foo> foos = new ArrayList<>(asList(
                new Foo(3),
                new Foo(5),
                new Foo(10),
                new Foo(12),
                new Foo(9)
        ));
        List<Foo> foos1 = foos;
        Iterator<Foo> iterator = foos1.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getValue() > 10) {
                iterator.remove();
            }
        }
        System.out.println(foos1.toString());

        //Using remove if
        List<Foo> foos2 = foos;
        foos2.removeIf(foo -> foo.getValue() > 10);
        System.out.println(foos2.toString());

        //Using predicate is readable
        List<Foo> foos3 = foos;
        Predicate<Foo> valueGreaterThan10 = foo -> foo.getValue() > 10;
        foos3.removeIf(valueGreaterThan10);
        System.out.println(foos3.toString());
    }
}


