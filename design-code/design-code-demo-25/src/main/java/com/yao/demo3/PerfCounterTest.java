package com.yao.demo3;

import com.yao.demo2.Interface.MetricsStorage;
import com.yao.demo2.MetricsCollector;
import com.yao.demo2.RedisMetricsStorage;
import com.yao.demo2.entry.RequestInfo;

/**
 * 重构： 主要是对Aggregator 和 ConsoleReporter、EmailReporter进行重构，并且将显示方式单独抽取出来作为1个接口，
 * 分别以控制台输出、邮件发送等方式的类去实现该接口
 *
 * @author pengjie_yao
 * @date 2020/3/13 17:30
 */
public class PerfCounterTest {

    public static void main(String[] args) {
        MetricsStorage storage = new RedisMetricsStorage();
        Aggregator aggregator = new Aggregator();

        // 定时触发统计并将结果显示到终端
        ConsoleViewer consoleViewer = new ConsoleViewer();
        ConsoleReporter consoleReporter = new ConsoleReporter(storage, aggregator, consoleViewer);
        consoleReporter.startRepeatedReport(60, 60);

        // 定时触发统计并将结果输出到邮件
        EmailViewer emailViewer = new EmailViewer();
        emailViewer.addToAddress("wangzheng@xzg.com");
        EmailReporter emailReporter = new EmailReporter(storage, aggregator, emailViewer);
        emailReporter.startDailyReport();

        // 收集接口访问数据
        MetricsCollector collector = new MetricsCollector(storage);
        collector.recordRequest(new RequestInfo("register", 123, 10234));
        collector.recordRequest(new RequestInfo("register", 223, 11234));
        collector.recordRequest(new RequestInfo("register", 323, 12334));
        collector.recordRequest(new RequestInfo("login", 23, 12434));
        collector.recordRequest(new RequestInfo("login", 1223, 14234));

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
