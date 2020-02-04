package org.tech.accumulation.demo.java.concurrent;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.LocalDateTime;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jiangpeiheng create on 2020/2/4
 */
@Slf4j
public class LockDemo {

    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        new ExeThread("t1").start();
        new ExeThread("t2").start();
        new ExeThread("t3").start();
        TimeUnit.SECONDS.sleep(7);

        new ExeThread("t4").start();
        TimeUnit.SECONDS.sleep(7);
    }

    @AllArgsConstructor
    public static class ExeThread extends Thread {

        private String name;

        @Override
        public void run() {
            if (lock.tryLock()) {
                try {
                    log.info("{} start", name);
                    TimeUnit.SECONDS.sleep(5);
                    log.info("{} end", name);
                } catch (Exception e) {
                    log.error("{} execute error, e:", name, e);
                } finally {
                    lock.unlock();
                }
            } else {
                log.error("{} Unable to get lock!", name);
            }
        }
    }

}
