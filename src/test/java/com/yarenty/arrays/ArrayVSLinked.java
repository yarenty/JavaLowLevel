package com.yarenty.arrays;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * OUTPUT:
 * <pre>
 *
 * </pre>
 *
 * @author yarenty
 */

public class ArrayVSLinked {

    private final static int WARMUP = 1000;
    private final static int TEST = 1000;
    private final static int SIZE = 500000;

    /**
     * Average time to build array  list:  43341445 ns  (43s)
     * Average time to build linked list: 200301282 ns (200s)
     * <p/>
     * <p/>
     * TODO:
     * - two different directions
     * - min & max
     * - distribution
     * article!!!
     */

    @Test
    public void perfTest() {
        System.out.println("Warmup");
        // Warmup
        for (int i = 0; i < WARMUP; ++i) {
            buildArrayList();
        }
        System.out.println("DONE.");
        // Test
        long sum = 0;
        for (int i = 0; i < TEST; ++i) {
            sum += buildArrayList();
        }
        System.out.println("Average time to build array list: " + (sum / TEST));


        // Warmup
        for (int i = 0; i < WARMUP; ++i) {
            buildLinkedList();
        }

        System.out.println("Linked warmup done");
        // Test
        sum = 0;
        for (int i = 0; i < TEST; ++i) {
            sum += buildLinkedList();
        }
        System.out
                .println("Average time to build linked list: " + (sum / TEST));
    }

    public long buildArrayList() {
        long start = System.nanoTime();
        ArrayList<Integer> a = new ArrayList<Integer>();
        for (int i = 0; i < SIZE; ++i) {
            a.add(i);
        }
        long end = System.nanoTime();
        return end - start;
    }

    public long buildLinkedList() {
        long start = System.nanoTime();
        LinkedList<Integer> a = new LinkedList<Integer>();
        for (int i = 0; i < SIZE; ++i) {
            a.add(i);
        }
        long end = System.nanoTime();
        return end - start;
    }
}
