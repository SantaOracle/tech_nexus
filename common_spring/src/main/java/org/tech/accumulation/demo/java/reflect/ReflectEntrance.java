package org.tech.accumulation.demo.java.reflect;

import lombok.extern.slf4j.Slf4j;

/**
 * @author peiheng.jiang create on 2019/8/31
 */
@Slf4j
public class ReflectEntrance {

    public static void reflectNewInstance() {
        try {
            Class<?> clazz = Class.forName("org.tech.accumulation.demo.java.reflect.ReflectBook");
            Object obj = clazz.newInstance();
            ReflectBook book = (ReflectBook) obj;
            book.setName("流浪地球")
                    .setAuthor("刘慈欣");
            log.info("Reflect new instance success, book:{}", book);
        } catch (Exception e) {
            log.error("Reflect new instance failed, e:", e);
        }
    }

    public static void main(String[] args) {
        reflectNewInstance();
    }

}
