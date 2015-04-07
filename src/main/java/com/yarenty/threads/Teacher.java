package com.yarenty.threads;

import java.util.concurrent.BlockingQueue;

/**
 * Created by yarenty on 30/07/14.
 */
public class Teacher extends Thread {

    private final BlockingQueue<String> queue;

    public static long time = System.currentTimeMillis();

    public void msg(String m) {
        System.out.println(getName() + " [" + (System.currentTimeMillis() - time) + "] " + ": ");
        System.out.println(m);
    }


    Teacher(BlockingQueue<String> q) {
        queue = q;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p/>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        try {
            System.out.println("Start " + Thread.currentThread().getName());

            String value = queue.take();
            while (!value.equals("*")) {

                // do something with value
                System.out.println(Thread.currentThread().getName() + " procassing message::" + value);

                value = queue.take();
            }
        } catch (Exception e) {
            System.out.println(" Error in teacher Q:" + e.getMessage());
        }

    }
}
