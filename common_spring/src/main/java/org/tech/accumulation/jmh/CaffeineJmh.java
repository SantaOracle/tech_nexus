package org.tech.accumulation.jmh;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author peiheng.jiang create on 2019/04/01
 */
@BenchmarkMode(Mode.AverageTime)            // 测试方法的平均执行时间
@OutputTimeUnit(TimeUnit.MICROSECONDS)      // 输出结果粒度为微妙
@State(Scope.Benchmark)                     // 所有测试共用一个实例
public class CaffeineJmh {

    private Cache<String, String> cache = Caffeine.newBuilder()
            .initialCapacity(2000)
            .expireAfterWrite(5, TimeUnit.MINUTES)
            .build();

    private Random random = new Random();

    private static final int DEF_RANDOM_RANGE = 4000;

    @Benchmark
    public void put() {
        String key = String.valueOf(getRandomInt(DEF_RANDOM_RANGE));
        String value = UUID.randomUUID().toString();
        cache.put(key, value);
    }

    @Benchmark
    public String get() {
        return cache.getIfPresent(String.valueOf(getRandomInt(DEF_RANDOM_RANGE)));
    }

    private int getRandomInt(int range) {
        return random.nextInt() % range;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(CaffeineJmh.class.getSimpleName())
                .forks(1)                   // 使用1个进程进行测试
                .warmupIterations(5)        // 执行5遍warmup
                .measurementIterations(5)   // 执行5遍测试
                .build();
        new Runner(opt).run();
    }
}
