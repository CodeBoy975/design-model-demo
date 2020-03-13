package com.yao.demo3;

import com.yao.demo2.entry.RequestInfo;
import com.yao.demo2.entry.RequestStat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 统计类
 * 重构： 主要是将代码进行解耦的过程，之前是直接一个静态方法实现： 各种统计数据以及显示。
 * 重构后，则是将方法进行抽取小个，将功能进行区分。显示部分则单独抽取开，不放在该类上。
 * 并且尽可能的按功能进行细分，各司其职，方便后期的解耦。
 *
 * @author pengjie_yao
 * @date 2020/3/13 17:19
 */
public class Aggregator {

    /**
     * 获取统计数据
     *
     * @param requestInfos  请求数据
     * @param durationInMillis  持续时间
     * @return
     */
    public Map<String, RequestStat> aggregate(Map<String, List<RequestInfo>> requestInfos, long durationInMillis) {
        Map<String, RequestStat> requestStats = new HashMap<>();
        for (Map.Entry<String, List<RequestInfo>> entry : requestInfos.entrySet()) {
            String apiName = entry.getKey();
            List<RequestInfo> requestInfosPerApi = entry.getValue();
            // 获取运算结果
            RequestStat requestStat = doAggregate(requestInfosPerApi, durationInMillis);
            requestStats.put(apiName, requestStat);
        }
        return requestStats;
    }

    /**
     * 做运算
     *
     * @param requestInfos
     * @param durationInMillis
     * @return
     */
    private RequestStat doAggregate(List<RequestInfo> requestInfos, long durationInMillis) {
        List<Double> respTimes = new ArrayList<>();
        for (RequestInfo requestInfo : requestInfos) {
            double respTime = requestInfo.getResponseTime();
            respTimes.add(respTime);
        }

        RequestStat requestStat = new RequestStat();
        // 计算最大值、最小值等各种运算并赋值到对象中
        requestStat.setMaxResponseTime(max(respTimes));
        requestStat.setMinResponseTime(min(respTimes));
        requestStat.setAvgResponseTime(avg(respTimes));
        requestStat.setP999ResponseTime(percentile999(respTimes));
        requestStat.setP99ResponseTime(percentile99(respTimes));
        requestStat.setCount(respTimes.size());
        requestStat.setTps((long) tps(respTimes.size(), durationInMillis / 1000));
        return requestStat;
    }

    /**
     * 计算最大值
     *
     * @param dataset
     * @return
     */
    private double max(List<Double> dataset) {
        return -1;
    }

    /**
     * 计算最小值
     *
     * @param dataset
     * @return
     */
    private double min(List<Double> dataset) {
        return -1;
    }

    /**
     * 计算平均值
     *
     * @param dataset
     * @return
     */
    private double avg(List<Double> dataset) {
        return -1;
    }

    /**
     * 计算tps
     *
     * @param count
     * @param duration
     * @return
     */
    private double tps(int count, double duration) {
        return -1;
    }

    /**
     * 计算999
     *
     * @param dataset
     * @return
     */
    private double percentile999(List<Double> dataset) {
        return -1;
    }

    /**
     * 计算99
     *
     * @param dataset
     * @return
     */
    private double percentile99(List<Double> dataset) {
        return -1;
    }

    /**
     * @param dataset
     * @param ratio
     * @return
     */
    private double percentile(List<Double> dataset, double ratio) {
        return -1;
    }
}