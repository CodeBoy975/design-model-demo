package com.yao.design.code.demo.demo2;

import lombok.Data;

/**
 *  api的状态信息
 * @author pengjie_yao
 * @date 2019/12/18 16:39
 */
@Data
public class ApiStatInfo {

    /**
     *  api名字
     */
    private String api;
    /**
     *  请求数
     */
    private long requestCount;
    /**
     * 错误数
     */
    private long errorCount;
    /**
     *  持续时间
     */
    private long durationOfSeconds;

    /**
     *  改动1：需求变动后所加的代码
     * 超时数
     */
    private long timeoutCount;
}
