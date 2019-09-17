package org.tech.accumulation.demo.java.collection;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.comparators.ComparatorChain;
import org.tech.accumulation.bean.User;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 多属性排序demo
 * Create by peiheng.jiang on 2019/9/17
 */
public class CompareChainDemo {

    private static final ComparatorChain<User> comparatorChain;

    static {
        ComparatorChain<User> tmpChain = new ComparatorChain<>();
        tmpChain.addComparator(Comparator.comparing(User::getUsername));
        tmpChain.addComparator(Comparator.comparingInt(User::getAge));
        tmpChain.addComparator(Comparator.comparingInt(User::getGender));
        comparatorChain = tmpChain;
    }

    public static void main(String[] args) {
        User u1 = new User().setUsername("Eden").setAge(32).setGender(1);
        User u2 = new User().setUsername("Eden").setAge(24).setGender(2);
        User u3 = new User().setUsername("Eden").setAge(24).setGender(1);
        User u4 = new User().setUsername("Alex").setAge(37).setGender(1);
        User u5 = new User().setUsername("Bill").setAge(21).setGender(2);

        List<User> uList = Lists.newArrayList(u1, u2, u3, u4, u5);
        System.out.println(uList);
        uList = uList.stream().sorted(comparatorChain).collect(Collectors.toList());
        System.out.println(uList);
    }
}
