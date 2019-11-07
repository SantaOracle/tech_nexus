package org.tech.accumulation.util;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

import java.util.Date;
import java.util.Map;

/**
 * Create by peiheng.jiang on 2019/10/18
 */
@Slf4j
public class JolUtil {

    public static void main(String[] args) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("a", 1);
        map.put("b", "b");
        map.put("c", new Date());
        System.out.println(classLayoutInfo(map));
        System.out.println("---------------------------------");
        System.out.println(graphLayoutInfo(map));
        System.out.println("---------------------------------");
        System.out.println(objTotalSize(map));
    }

    /**
     * 查看对象内部信息
     * @param obj
     * @return
     */
    public static String classLayoutInfo(Object obj) {
        return ClassLayout.parseInstance(obj).toPrintable();
    }

    /**
     * 查看对象外部信息，包括引用对象
     * @param obj
     * @return
     */
    public static String graphLayoutInfo(Object obj) {
        return GraphLayout.parseInstance(obj).toPrintable();
    }

    /**
     * 查看对象总大小
     * @param obj
     * @return
     */
    public static long objTotalSize(Object obj) {
        return GraphLayout.parseInstance(obj).totalSize();
    }
}
