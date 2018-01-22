package com.lamdevops.lambda;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by lamdevops on 8/11/17.
 */
public class ConsumerExample {

    @Test
    public void demo1() throws Exception {
        Consumer<String> c = (x) -> System.out.println(x.toLowerCase());
        c.accept("Java2s.com");
    }

    @Test
    public void demo2() throws Exception {
        int x = 99;

        Consumer<Integer> myCom = (y) -> {
            System.out.println("x = " + x);
            System.out.println("y = " + y);
        };

        myCom.accept(x);
    }

    @Test
    public void demo3() throws Exception {
        List<Student> students = Arrays.asList(
                new Student("John", 3),
                new Student("Mark", 4),
                new Student("Pete", 6));

        acceptAllEmployee(students, e -> System.out.println(e.getName()));
        acceptAllEmployee(students, e -> {
            e.setGpa(e.getGpa() * 1.5);
        });

        acceptAllEmployee(students, e -> System.out.println(e.getName() + ": " + e.getGpa()));

    }

    @Test
    public void demo4() throws Exception {

    }

    public static void acceptAllEmployee(List<Student> students, Consumer<Student> printer) {
        students.stream().forEach(student -> printer.accept(student));
    }

    private static class Student {
        private String name;
        private double gpa;

        public Student(String name, double gpa) {
            this.name = name;
            this.gpa = gpa;
        }

        public void setGpa(double gpa) {
            this.gpa = gpa;
        }

        public String getName() {
            return name;
        }

        public double getGpa() {
            return gpa;
        }
    }
}
