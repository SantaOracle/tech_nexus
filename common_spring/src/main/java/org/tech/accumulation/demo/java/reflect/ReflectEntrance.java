package org.tech.accumulation.demo.java.reflect;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;

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

    public static void reflectPrivateConstructor() {
        try {
            Class<?> clazz = Class.forName("org.tech.accumulation.demo.java.reflect.ReflectBook");
            Constructor<?> constructor = clazz.getDeclaredConstructor(String.class, String.class);
            constructor.setAccessible(true);
            ReflectBook book = (ReflectBook) constructor.newInstance("OverWatch", "Blizzard");
            log.info("Reflect private constructor success, book:{}", book);
        } catch (Exception e) {
            log.error("Reflect private constructor failed, e:", e);
        }
    }

    public static void main(String[] args) {
        reflectNewInstance();
        reflectPrivateConstructor();
    }

}
