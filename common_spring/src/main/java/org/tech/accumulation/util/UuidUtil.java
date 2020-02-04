package org.tech.accumulation.util;

import java.util.UUID;

/**
 * @author hk
 * @date 2019/10/24
 */
public class UuidUtil {
    public static String get() {
        return UUID.randomUUID().toString();
    }

    public static String getWithoutLink() {
        return get().replaceAll("-", "");
    }

    public static void main(String[] args) {
        System.out.println(getWithoutLink());
    }
}
