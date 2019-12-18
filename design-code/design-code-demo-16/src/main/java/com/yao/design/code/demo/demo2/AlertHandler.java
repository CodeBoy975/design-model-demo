package com.yao.design.code.demo.demo2;

import javax.management.Notification;

/**
 *  报警拦截器 这里设置为抽象，方便进行扩展
 * @author pengjie_yao
 * @date 2019/12/18 16:38
 */
public abstract class AlertHandler {

    /**
     *  规则
     */
    protected AlertRule rule;
    /**
     *  通知
     */
    protected Notification notification;

    public AlertHandler(AlertRule rule, Notification notification) {
        this.rule = rule;
        this.notification = notification;
    }

    /**
     *  设置为抽象方式，则继承该类都要重写，则主要是为了后续扩展
     * @param apiStatInfo
     */
    public abstract void check(ApiStatInfo apiStatInfo);
}
