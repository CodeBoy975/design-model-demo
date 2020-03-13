package com.yao.demo3;

import com.yao.demo2.Interface.MetricsStorage;
import com.yao.demo2.entry.RequestInfo;
import com.yao.demo2.entry.RequestStat;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 入口类，将各个数据进行封装
 * 组装类并定时触发执行统计显示。在将核心逻辑剥离出来之后，这个类的代码变得更加简洁、清晰，
 * 只负责组装各个类（MetricsStorage、Aggegrator、StatViewer）来完成整个工作流程
 *
 * @author pengjie_yao
 * @date 2020/3/13 17:27
 */
public class ConsoleReporter {
    private MetricsStorage metricsStorage;
    private Aggregator aggregator;
    private StatViewer viewer;
    private ScheduledExecutorService executor;

    /**
     * 构造器，初始化个个组件
     *
     * @param metricsStorage
     * @param aggregator
     * @param viewer
     */
    public ConsoleReporter(MetricsStorage metricsStorage, Aggregator aggregator, StatViewer viewer) {
        this.metricsStorage = metricsStorage;
        this.aggregator = aggregator;
        this.viewer = viewer;
        this.executor = Executors.newSingleThreadScheduledExecutor();
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
