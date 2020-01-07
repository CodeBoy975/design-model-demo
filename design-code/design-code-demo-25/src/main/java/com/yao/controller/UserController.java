package com.yao.controller;

import com.yao.module.UserVo;
import org.springframework.stereotype.Controller;

import java.util.concurrent.TimeUnit;

/**
 * 应用场景：统计下面两个接口(注册和登录）的响应时间和访问次数
 * @author pengjie_yao
 * @date 2020/1/7 17:54
 */
@Controller
public class UserController {
    private Metrics metrics = new Metrics();

    public UserController() {
        metrics.startRepeatedReport(60, TimeUnit.SECONDS);
    }

    /**
     *  注册
     * @param user
     */
    public void register(UserVo user) {
        long startTimestamp = System.currentTimeMillis();
        metrics.recordTimestamp("register", startTimestamp);
        //...
        long respTime = System.currentTimeMillis() - startTimestamp;
        metrics.recordResponseTime("register", respTime);
    }

    /**
     *  登录
     * @param telephone
     * @param password
     * @return
     */
    public UserVo login(String telephone, String password) {
        long startTimestamp = System.currentTimeMillis();
        metrics.recordTimestamp("login", startTimestamp);
        //...
        long respTime = System.currentTimeMillis() - startTimestamp;
        metrics.recordResponseTime("login", respTime);
    }
}
