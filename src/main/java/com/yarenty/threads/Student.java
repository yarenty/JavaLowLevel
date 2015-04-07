package com.yarenty.threads;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * Created by yarenty on 30/07/14.
 */
public class Student extends Thread {

    private final BlockingQueue<String> queue;
    private final String name;

    public static final long time = System.currentTimeMillis();

    public void msg(final String m) {
        System.out.println(getName() + " [" + (System.currentTimeMillis() - time) + "] " + ": ");
        System.out.println(m);
    }


    Student(final String name, final BlockingQueue<String> q) {
        queue = q;
        this.name = name;
    }

//    // Default constructor
//    public RandomThread(int id) {
//        setName("ThreadName-" + id);
//    }

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

            sleep(Math.abs(new Random().nextInt(10)));

            final int priority = this.getPriority();
            this.setPriority(Thread.MAX_PRIORITY);
            queue.add(name + "test");
            this.setPriority(priority);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
