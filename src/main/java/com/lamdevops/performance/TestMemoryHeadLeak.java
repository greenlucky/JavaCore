package com.lamdevops.performance;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class TestMemoryHeadLeak {

    private Random random = new Random();
    public static final List<Double> list = new ArrayList<>(1000000);

    @Test
    public void test() throws InterruptedException {
        for (int i = 0; i < 1000000; i++){
            list.add(random.nextDouble());
        }

        System.gc();
        Thread.sleep(100000);
    }


    @Test
    public void test1() throws InterruptedException {
        addElementsToTheList();
        System.gc();
        Thread.sleep(100000); // to allow GC do its job
    }

    private void addElementsToTheList(){
        ArrayList<Double> list = new ArrayList<Double>(1000000);
        for (int i = 0; i < 1000000; i++) {
            list.add(random.nextDouble());
        }
    }

    /**
     * Test add object to set, which object without hashCode() and equals
     * @throws Exception
     */
    @Test
    public void testAddObjectToSetWithoutHastCodeAndEquals() throws Exception {
        Map<Object, Object> map = System.getProperties();
        while (true) {
            map.put(new Key("key"), "value");
        }
    }

    /**
     * Test add object to set, which object with hashCode() and equals
     * @throws Exception
     */
    @Test
    public void testAddObjectToSetWithHashCodeAndEquals() throws Exception {
        Map<Object, Object> map = System.getProperties();
        while (true) {
            map.put(new Key("key"), "value");
        }
    }
}
