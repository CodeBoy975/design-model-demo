package com.yao.design.code.demo.demo2;

import javax.management.Notification;

/**
 *  超时拦截
 *  todo 改动2：需求变动后所加的代码，添加新的handler
 * @author pengjie_yao
 * @date 2019/12/18 16:42
 */
public class TimeoutAlertHandler extends AlertHandler {

    public TimeoutAlertHandler(AlertRule rule, Notification notification) {
        super(rule, notification);
    }

    @Override
    public void check(ApiStatInfo apiStatInfo) {

        long tps = apiStatInfo.getRequestCount() / apiStatInfo.getDurationOfSeconds();
        if (tps > rule.getMatchedRule(apiStatInfo.getApi()).getMaxTps()) {
            notification.notify(NotificationEmergencyLevel.SEVRE, "...");
        }
    }
}
