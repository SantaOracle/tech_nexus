package org.tech.accumulation.demo.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;
import org.tech.accumulation.demo.sentinel.service.HelloWorldImpl;
import org.tech.accumulation.demo.sentinel.service.IHelloWorld;

/**
 * @author peiheng.jiang create on 2019/8/27
 */
@Service
public class SentinelEntrance {

    public static final String RESOURCE_NAME = "HelloWorldResource";

    private static IHelloWorld helloWorldImpl = new HelloWorldImpl();

    public static void main(String[] args) {
        try (Entry entry = SphU.entry(RESOURCE_NAME)) {
            String res = helloWorldImpl.sayHello("Jiang");
            System.out.println("Get result:" + res);
        } catch (BlockException e) {
            System.out.println("Resource is blocked!");
        }
    }
}
