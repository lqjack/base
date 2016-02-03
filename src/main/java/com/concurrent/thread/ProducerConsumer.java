package com.concurrent.thread;

import java.util.Stack;
import java.util.concurrent.TimeUnit;

/**
 * <p/>
 * Date: 2015/4/11
 * Time: 12:00
 */
public class ProducerConsumer {

    static Stack<String> message = new Stack<String>();

    final static int MAX_SIZE = 10;

    public static void main(String[] args) {
        Thread producer = new Thread(new Producer(), "Producer-1");
        Thread consumer1 = new Thread(new Consumer(), "Consumer-1");
        Thread consumer2 = new Thread(new Consumer(), "Consumer-2");

        producer.start();
        consumer1.start();
        consumer2.start();

//        try {
//            consumer1.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }


    static class Producer implements Runnable {
        public void produce(String msg) {
            System.out.println("Producing " + msg);
            message.push(msg);
        }

        public void run() {
            int i = 0;

            while (i++ < MAX_SIZE) {
                synchronized (message) {
                    produce("message" + i);
                    message.notifyAll();
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    static class Consumer implements Runnable {
        @Override
        public void run() {
            synchronized (message) {
                while (true) {
                    while (message.isEmpty()) {
                        try {
                            message.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    consumer();
                }
            }
        }

        private void consumer() {
            System.out.println(Thread.currentThread().getName() + "　" + message.pop());
        }
    }
}