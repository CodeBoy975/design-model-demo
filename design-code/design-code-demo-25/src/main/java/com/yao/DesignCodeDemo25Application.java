package com.yao;

import com.yao.demo1.Metrics;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 *  应用场景：统计下面两个接口(注册和登录）的响应时间和访问次数
 * @author pengjie_yao
 */
@SpringBootApplication
public class DesignCodeDemo25Application {

    public static void main(String[] args) {
        SpringApplication.run(DesignCodeDemo25Application.class, args);
    }

    @Bean
    public Metrics metrics() {
        return new Metrics();
    }
}
