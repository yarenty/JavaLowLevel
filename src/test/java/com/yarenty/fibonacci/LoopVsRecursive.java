package com.yarenty.fibonacci;

import org.junit.Test;

/**
 * OUTPUT:
 * <pre>
 * Loop start
 * 1 1 2 3 5 8 13 21 34 55 89 144 233 377 610 987 1597 2584 4181 Loop F up to 20:8,451 ns
 * Recursive start
 * 1 1 2 3 5 8 13 21 34 55 89 144 233 377 610 987 1597 2584 4181 Recursive F up to 20:140,510 ns
 *
 * </pre>
 *
 * @author yarenty
 */
public class LoopVsRecursive {

    static final int NUM = 21;

    @Test
    public void testRecursive() {
        System.out.println("Recursive start");

        //warm up
        for (int i = 1; i < NUM; i++) recursive(i);
        for (int i = 1; i < NUM; i++) recursive(i);


        final Timer t = new Timer();
        t.start();
        for (int i = 1; i < NUM; i++) recursive(i);
        t.stop();

        for (int i = 1; i < NUM; i++) {
            System.out.print(recursive(i) + " ");
        }
        System.out.println(t.display("Recursive F up to " + NUM));
    }

    @Test
    public void testLoop() {
        System.out.println("Loop start");

        //warm up
        for (int i = 1; i < NUM; i++) loop(i);
        for (int i = 1; i < NUM; i++) loop(i);


        final Timer t = new Timer();
        t.start();
        for (int i = 1; i < NUM; i++) loop(i);
        t.stop();

        for (int i = 1; i < NUM; i++) {
            System.out.print(loop(i) + " ");
        }
        System.out.println(t.display("Loop F up to " + NUM));
    }


    public static int recursive(final int num) {
        if (num == 1 || num == 2) {
            return 1;
        }
        return recursive(num - 1) + recursive(num - 2);
    }


    public static int loop(final int num) {
        if (num == 1 || num == 2) {
            return 1;
        }
        int f1 = 1, f2 = 1, fib = 1;

        for (int i = 3; i <= num; i++) {
            fib = f1 + f2;
            f1 = f2;
            f2 = fib;
        }
        return fib;
    }


    class Timer {

        long start = 0;
        long stop = 0;

        public void start() {
            start = System.nanoTime();
        }

        public void stop() {
            stop = System.nanoTime();
        }

        public String stopAndDisplay(String text) {
            stop();
            return text + ":" + String.format("%,d", (stop - start)) + " ns";

        }

        public String display(String text) {
            if (stop == 0) stop();
            return text + ":" + String.format("%,d", (stop - start)) + " ns";
        }

    }

}

