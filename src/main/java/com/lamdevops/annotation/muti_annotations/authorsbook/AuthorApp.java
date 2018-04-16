package com.lamdevops.annotation.muti_annotations.authorsbook;

import java.util.Arrays;

/**
 * Created by lamdevops on 8/6/17.
 */
public class AuthorApp {

    public static void main(String[] args) throws Exception {
        Author[] authors = Book.class.getAnnotationsByType(Author.class);
        Arrays.asList(authors).forEach(a -> System.out.println(a.name()));
    }
}
