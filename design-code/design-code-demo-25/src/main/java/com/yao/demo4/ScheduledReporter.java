package com.yao.demo4;

import com.yao.demo3.Aggregator;
import com.yao.demo2.Interface.MetricsStorage;
import com.yao.demo2.entry.RequestInfo;
import com.yao.demo2.entry.RequestStat;
import com.yao.demo3.StatViewer;

import java.util.List;
import java.util.Map;

/**
 * 抽象的父类，该类主要是解决代码重复问题(ConsoleReporter 和 EmailReporter)
 *
 * @author pengjie_yao
 * @date 2020/3/18 14:36
 */
public abstract class ScheduledReporter {
    protected MetricsStorage metricsStorage;
    protected Aggregator aggregator;
    protected StatViewer viewer;

    public ScheduledReporter(MetricsStorage metricsStorage, Aggregator aggregator, StatViewer viewer) {
        this.metricsStorage = metricsStorage;
        this.aggregator = aggregator;
        this.viewer = viewer;
    }

    /**
     * 做统计 该方法在ConsoleReporter和EmailReporter重复，故抽取为父类
     *
     * @param startTimeInMillis
     * @param endTimeInMillis
     */
    protected void doStatAndReport(long startTimeInMillis, long endTimeInMillis) {
        long durationInMillis = endTimeInMillis - startTimeInMillis;
        Map<String, List<RequestInfo>> requestInfos = metricsStorage.getRequestInfos(startTimeInMillis, endTimeInMillis);
        Map<String, RequestStat> requestStats = aggregator.aggregate(requestInfos, durationInMillis);
        viewer.output(requestStats, startTimeInMillis, endTimeInMillis);
    }

}
