package com.yao.demo2.entry;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.Data;

import java.beans.ConstructorProperties;

/**
 * @author pengjie_yao
 * @date 2020/3/13 14:41
 */
@Data
public class RequestInfo {

    /**
     * 接口名
     */
    private String apiName;

    /**
     * 响应时间
     */
    private double responseTime;

    /**
     * 返回时间
     */
    private double timestamp;

    public RequestInfo(String apiName, double responseTime, long timestamp) {
        this.apiName = apiName;
        this.responseTime = responseTime;
        this.timestamp = timestamp;
    }
}
