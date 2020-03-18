package com.yao.demo4;

import com.yao.demo2.Interface.MetricsStorage;
import com.yao.demo2.RedisMetricsStorage;
import com.yao.demo2.entry.RequestInfo;
import com.yao.demo2.entry.RequestStat;
import com.yao.demo3.Aggregator;
import com.yao.demo3.ConsoleViewer;
import com.yao.demo3.StatViewer;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author pengjie_yao
 * @date 2020/3/18 14:38
 */
public class ConsoleReporter extends ScheduledReporter {

    private static final Long DAY_HOURS_IN_SECONDS = 86400L;

    private ScheduledExecutorService executor;


    /**
     * 带参的构造器
     *
     * @param metricsStorage
     * @param aggregator
     * @param viewer
     */
    public ConsoleReporter(MetricsStorage metricsStorage, Aggregator aggregator, StatViewer viewer) {
        super(metricsStorage, aggregator, viewer);
    }

    /**
     * 默认的构造器
     */
    public ConsoleReporter() {
        this(new RedisMetricsStorage(), new Aggregator(), new ConsoleViewer());
    }

    /**
     * 开始进行定时任务调度，分别调用个个组件
     *
     * @param periodInSeconds
     * @param durationInSeconds
     */
    public void startRepeatedReport(long periodInSeconds, long durationInSeconds) {
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                long durationInMillis = durationInSeconds * 1000;
                long endTimeInMillis = System.currentTimeMillis();
                long startTimeInMillis = endTimeInMillis - durationInMillis;
                // 1.保存数据
                Map<String, List<RequestInfo>> requestInfos = metricsStorage.getRequestInfos(startTimeInMillis, endTimeInMillis);
                // 2.做统计
                Map<String, RequestStat> requestStats = aggregator.aggregate(requestInfos, durationInMillis);
                // 3.师徒显示
                viewer.output(requestStats, startTimeInMillis, endTimeInMillis);
            }
        }, 0L, periodInSeconds, TimeUnit.SECONDS);
    }
}
