package org.tech.accumulation.demo.pattern;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 生产者，消费者模型
 * Create by peiheng.jiang on 2019/9/9
 */
public class ProConPattern {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        new Thread(new ProConPattern.Consumer(list)).start();
        new Thread(new ProConPattern.Provider(list)).start();
    }

    static class Consumer implements Runnable {

        private final List<Integer> list;

        public Consumer(final List<Integer> list) {
            this.list = list;
        }

        @Override
        public void run() {
            func();
        }

        private void func() {
            while (true) {
                try {
                    synchronized (list) {
                        while (list.isEmpty()) {
                            list.wait();
                        }
                        list.notify();
                        System.out.println("remove " + list.remove(0));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }

    static class Provider implements Runnable {
        private final List<Integer> list;

        public Provider(final List<Integer> list) {
            this.list = list;
        }

        @Override
        public void run() {
            func();
        }

        private void func() {
            while (true) {
                try {
                    synchronized (list) {
                        while (!list.isEmpty()) {
                            list.wait();
                        }
                        int i = ThreadLocalRandom.current().nextInt();
                        list.add(i);
                        list.notify();
                        System.out.println("add " + i);
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }
}
