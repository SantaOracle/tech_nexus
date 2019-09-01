package org.tech.accumulation.demo.sentinel.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author peiheng.jiang create on 2019/8/27
 */
@Service
@Slf4j
public class HelloWorldImpl implements IHelloWorld{

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
