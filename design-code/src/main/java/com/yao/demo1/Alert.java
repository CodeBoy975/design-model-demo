package com.yao.demo1;

import javax.management.Notification;

/**
 * @author pengjie_yao
 * @date 2019/12/18 16:27
 */
public class Alert {

    private AlertRult rule;

    private Notification notification;

    public Alert(AlertRult rule, Notification notification) {
        this.rule = rule;
        this.notification = notification;
    }

    /**
     *  原代码
     * 当接口的 TPS 超过某个预先设置的最大值时，
     * 以及当接口请求出错数大于某个最大允许值时，
     * 就会触发告警，通知接口的相关负责人或者团队。
     * @param api
     * @param requestCount
     * @param errorCount
     * @param durationOfSeconds
     */
    public void check(String api, long requestCount, long errorCount, long durationOfSeconds) {
        long tps = requestCount / durationOfSeconds;
        if (tps > rule.getMatchedRule(api).getMaxTps()) {
            notification.notify(NotificationEmergencyLevel.URGENCY, "...");
        }
        if (errorCount > rule.getMatchedRule(api).getMaxErrorCount()) {
            notification.notify(NotificationEmergencyLevel.SEVERE, "...");
        }
    }

    /**若此时，我们需要添加一个功能，当每秒接口超时请求个数，超过某个预先设置的最大阈值，则也要触发报警发送通知**/

    /**---------基于上面的代码，和需求改动后的代码如下---------**/

    /**
     *
     * 改动代码1：方法参数增加timeoutCount
     * 改动代码二： 添加了接口超时处理逻辑
     * 
     * 这样的坏处： 我们对接口进行了修改，也就意味着调用该接口代码都要做相应的修改，另一方面仅，修改了check函数，相应的单元测试都要进行修改
     * @param api
     * @param requestCount
     * @param errorCount
     * @param durationOfSeconds
     * @param timeoutCount
     */
    public void check(String api, long requestCount, long errorCount, long durationOfSeconds,long timeoutCount) {
        long tps = requestCount / durationOfSeconds;
        if (tps > rule.getMatchedRule(api).getMaxTps()) {
            notification.notify(NotificationEmergencyLevel.URGENCY, "...");
        }
        if (errorCount > rule.getMatchedRule(api).getMaxErrorCount()) {
            notification.notify(NotificationEmergencyLevel.SEVERE, "...");
        }


        // 改动代码二： 添加了接口超时处理逻辑
        long timeoutTps = timeoutCount / durationOfSeconds;
        if (timeoutTps > rule.getMatchedRult(api).getMaxTimeoutTps()) {
            notification.notify(NotificationEmergencyLevel.URGENCY,"...");
        }
    }
}
