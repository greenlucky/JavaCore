package com.lamdevops.collection.stream.convertlist2map;


import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ListToMap {

    @Test
    public void listToHashMap() {
        List<Student> students = new ArrayList<>();

        Map<Long, String> studentMap = students.stream().collect(Collectors.toMap(Student::getId, Student::getName));
    }

    /**
     * The Collectors.toMap()  fails when converting a list with duplicate items.
     * <p>
     * In order to handle duplicates, you can pass a third argument that informs the  toMap()  which value to consider when facing duplicate items.
     * If you want to override the existing value on duplicates, use  (oldValue, newValue) -> newValue .
     * </p>
     */
    @Test
    public void listToHashMapWithDuplicates() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(2L, "John"));
        students.add(new Student(1L, "Marry"));
        students.add(new Student(4L, "Nathan"));
        students.add(new Student(3L, "Henry"));
        Map<Long, String> studentMap = students.stream().collect(Collectors.toMap(Student::getId, Student::getName,
                (oldValue, newValue) -> oldValue));
        System.out.println(studentMap.toString());
    }

    /**
     * In order to preserve the order of the list items inside the  Map , you can pass another parameter to  Collectors.toMap()
     * which decides what type of map to use.  LinkedHashMap  is well known in preserving the order of its entries.
     */
    @Test
    public void listToHashMapPreserveOrder() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(2L, "John"));
        students.add(new Student(1L, "Marry"));
        students.add(new Student(4L, "Nathan"));
        students.add(new Student(3L, "Henry"));
        Map<Long, String> studentMap = students.stream().collect(Collectors.toMap(Student::getId, Student::getName,
                (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        System.out.println(studentMap.toString());
    }
}
