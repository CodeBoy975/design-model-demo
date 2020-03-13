package com.yao.demo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 实现功能： 登录注册接口的访问时间和响应时间
 *
 * @author pengjie_yao
 * @date 2020/3/12 10:21
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private Metrics metrics;

    public UserController(Metrics metrics) {
        this.metrics = metrics;
        // 开启启动定时记录
        metrics.startRepeatedReport(3, TimeUnit.SECONDS);
    }


    /**
     * 登录
     *
     * @param user
     * @return
     */
    @GetMapping("/login")
    public String login(User user) {
        long startTimestamp = System.currentTimeMillis();
        // 记录访问时间
        metrics.recordTimesstamp("login", startTimestamp);
        System.out.println("登录等操作中");
        long respTime = System.currentTimeMillis() - startTimestamp;
        // 记录响应时间
        metrics.recordResponseTime("login", respTime);
        return "";
    }

    /**
     * 注册
     *
     * @param user
     * @return
     */
    @GetMapping("/register")
    public String register(User user) {
        long startTimestamp = System.currentTimeMillis();
        // 记录访问时间
        metrics.recordTimesstamp("register", startTimestamp);
        System.out.println("注册等操作中");
        long respTime = System.currentTimeMillis() - startTimestamp;
        // 记录响应时间
        metrics.recordResponseTime("register", respTime);
        return "";
    }
}
