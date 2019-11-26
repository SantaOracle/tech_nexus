package org.tech.accumulation.demo.ttl;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * @author jiangpeiheng create on 2019/11/26
 */
public class TtlThreadlocalUtil {

    private static TransmittableThreadLocal<String> instance = new TransmittableThreadLocal<>();

    public static void set(String s) {
        instance.set(s);
    }

    public static String get() {
        return instance.get();
    }

    public static void clear() {
        instance.remove();
    }
}
