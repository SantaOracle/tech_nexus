package org.tech.accumulation.demo.ttl;

import com.alibaba.ttl.threadpool.TtlExecutors;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author jiangpeiheng create on 2019/11/26
 */
@Slf4j
public class TtlDemo {

    public static void main(String[] args) {
        String s = "abc";
        TtlThreadlocalUtil.set(s);

        ExecutorService es = getExecutorService();
        es.execute(() -> {

            log.info("Child thread data:{}", TtlThreadlocalUtil.get());
            TtlThreadlocalUtil.clear();
            log.info("Child thread data after clear:{}", TtlThreadlocalUtil.get());
        });

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            log.error("interrupt, e:", e);
        }
        log.info("Father thread data:{}", TtlThreadlocalUtil.get());
        TtlThreadlocalUtil.clear();
        log.info("Father thread data after clear:{}", TtlThreadlocalUtil.get());

        es.shutdown();
    }

    public static ExecutorService getExecutorService() {
        ExecutorService originEs = Executors.newFixedThreadPool(8);
        return TtlExecutors.getTtlExecutorService(originEs);
    }

}
