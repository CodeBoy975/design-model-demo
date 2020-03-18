package com.yao.demo4;

import com.google.common.annotations.VisibleForTesting;
import com.yao.demo2.RedisMetricsStorage;
import com.yao.demo3.Aggregator;
import com.yao.demo2.Interface.MetricsStorage;
import com.yao.demo3.ConsoleViewer;
import com.yao.demo3.StatViewer;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import static com.yao.demo3.EmailReporter.DAY_HOURS_IN_SECONDS;

/**
 * @author pengjie_yao
 * @date 2020/3/18 14:38
 */
public class EmailReporter extends ScheduledReporter {
    private static final Long DAY_HOURS_IN_SECONDS = 86400L;

    /**
     * 带参的构造器
     *
     * @param metricsStorage
     * @param aggregator
     * @param viewer
     */
    public EmailReporter(MetricsStorage metricsStorage, Aggregator aggregator, StatViewer viewer) {
        super(metricsStorage, aggregator, viewer);
    }

    /**
     * 默认的构造器
     */
    public EmailReporter() {
        this(new RedisMetricsStorage(), new Aggregator(), new ConsoleViewer());
    }


    public void startDailyReport() {
        Date firstTime = trimTimeFieldsToZeroOfNextDay();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // 省略其他代码...
            }
        }, firstTime, 1000);
    }

    // 设置成protected而非private是为了方便写单元测试
    @VisibleForTesting
    protected Date trimTimeFieldsToZeroOfNextDay() {
        Calendar calendar = Calendar.getInstance(); // 这里可以获取当前时间
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}