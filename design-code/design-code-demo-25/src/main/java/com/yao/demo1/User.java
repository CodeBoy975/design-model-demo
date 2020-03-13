package com.yao.demo1;

import lombok.Data;

/**
 * @author pengjie_yao
 * @date 2020/3/12 10:23
 */
@Data
public class User {

    /**
     * 用户名
     */
    private String name;

    /**
     * 年龄
     */
    private String age;

    /**
     * 密码
     */
    private String password;
}
