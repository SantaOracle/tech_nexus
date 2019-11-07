package org.tech.accumulation.demo;

import com.google.common.collect.Maps;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Create by peiheng.jiang on 2019/10/17
 */
public class TestDemo {

    public static void main(String[] args) {
        // offset 0
        System.out.println((1 >> (1 - 1) & 1));
        System.out.println((2 >> (1 - 1) & 1));
        System.out.println((3 >> (1 - 1) & 1));
        // offset 1
        System.out.println((1 >> (2 - 1) & 1));
        System.out.println((2 >> (2 - 1) & 1));
        System.out.println((3 >> (2 - 1) & 1));
    }
}
