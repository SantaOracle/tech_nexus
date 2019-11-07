package org.tech.accumulation.demo.java.threadpool;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Create by peiheng.jiang on 2019/11/5
 */
public class StopScheduleTaskDemo {

    public static void main(String[] args) {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(4);
        ScheduleTask task1 = new ScheduleTask();
        ScheduledFuture sf1 = executor.scheduleWithFixedDelay(task1, 0, 100, TimeUnit.MILLISECONDS);
        task1.setSf(sf1);

        ScheduleTask task2 = new ScheduleTask();
        ScheduledFuture sf2 = executor.scheduleWithFixedDelay(task2, 0, 100, TimeUnit.MILLISECONDS);
        task2.setSf(sf2);
    }

    private static class ScheduleTask implements Runnable {

        private AtomicInteger runTimes = new AtomicInteger(0);

        @Setter
        private ScheduledFuture sf;

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ", Running for " + runTimes.toString() + " time");
            runTimes.addAndGet(1);
            if (runTimes.get() >= 10) {
                stopTask();
            }
        }

        private void stopTask() {
            sf.cancel(true);
        }
    }
}
