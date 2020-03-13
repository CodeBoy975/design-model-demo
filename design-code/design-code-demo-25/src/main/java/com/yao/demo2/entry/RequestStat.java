package com.yao.demo2.entry;

import lombok.Data;

/**
 * @author pengjie_yao
 * @date 2020/3/13 14:45
 */
@Data
public class RequestStat {

    /**
     * 最大返回时间
     */
    private double maxResponseTime;
    /**
     * 最小返回时间
     */
    private double minResponseTime;
    /**
     * 平均返回时间
     */
    private double avgResponseTime;
    /**
     * 999返回时间
     */
    private double p999ResponseTime;
    /**
     * 99返回时间
     */
    private double p99ResponseTime;
    /**
     * 访问次数
     */
    private long count;
    /**
     * tps
     */
    private long tps;
}
