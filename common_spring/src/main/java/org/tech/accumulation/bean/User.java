package org.tech.accumulation.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * create by peiheng.jiang on 2019/7/30
 */
@Data
@Accessors(chain = true)
public class User implements Serializable {

    private String username;
    private String password;
    private String phone;
    private int gender;
    private int age;
}
