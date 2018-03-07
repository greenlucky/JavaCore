package com.lamdevops.generic;

import com.lamdevops.collection.stream.pojo.Item;

import java.util.ArrayList;

public class Arrays {
    public static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void printNames(ArrayList<? extends Item> items) {
        items.stream().forEach(item -> System.out.println(item));
    }

}
