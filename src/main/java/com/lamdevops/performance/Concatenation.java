package com.lamdevops.performance;

public class Concatenation {

    public static void main(String[] args) throws InterruptedException {
        operator();
        stringBuilder();
        stringBuffer();

        System.gc();
        Thread.sleep(100000);
    }

    private static void operator() {
        long startTime = System.currentTimeMillis();

        String str = "Test";

        for(int i = 0; i <= 100000; i++) {
            str += i + " ";
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Finish concat time: " + (endTime - startTime));
    }

    private static void stringBuilder() {
        long startTime = System.currentTimeMillis();

        StringBuilder str = new StringBuilder("Test ");

        for(int i = 0; i <= 100000; i++) {
            str.append(i).append(" ");
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Finish concat time: " + (endTime - startTime));
    }

    private static void stringBuffer() {
        long startTime = System.currentTimeMillis();

        StringBuffer str = new StringBuffer("Test ");

        for(int i = 0; i <= 100000; i++) {
            str.append(i).append(" ");
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Finish concat time: " + (endTime - startTime));
    }

}
