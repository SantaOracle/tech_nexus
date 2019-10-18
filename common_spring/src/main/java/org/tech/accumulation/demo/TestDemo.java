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
        Map<Integer, Integer> map = Maps.newHashMap();
        map.put(1, 10);
        map.put(2, 34);
        map.put(3, 56);
        map.put(4, 77);
        map.put(5, 78);
        map.put(6, 78);

        List<Integer> priceSizeList = map.values().stream()
                .sorted(Comparator.comparingInt(o -> (int) o).reversed())
                .collect(Collectors.toList());

        System.out.println(priceSizeList);
    }
}
