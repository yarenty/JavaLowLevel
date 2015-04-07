package com.yarenty.threads;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {

    public static void main(final String[] args) throws Exception {

        final int capacity = 10;
        final BlockingQueue<String> q = new LinkedBlockingQueue<String>(capacity);

        final Thread t = new Thread(new Teacher(q));
        final Thread s1 = new Thread(new Student("11", q));
        final Thread s2 = new Thread(new Student("22", q));
        final Thread s3 = new Thread(new Student("33", q));

        t.start();
        s1.start();
        s2.start();
        s3.start();

        t.join();
        s1.join();
        s2.join();
        s3.join();

        System.out.println("FINISH");


    }
}
