package com.lamdevops.generic;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArraysTest {

    @Test
    public void swap() throws Exception {
        String[] friends = {"Lam", "Thao", "Hung", "Dang"};
        printArray(friends);
        Arrays.swap(friends, 0, 1);
        printArray(friends);
    }

    private <T> void printArray(T[] array) {
        for(int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println("");
    }
}