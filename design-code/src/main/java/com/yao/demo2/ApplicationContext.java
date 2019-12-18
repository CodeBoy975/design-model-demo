package com.yao.demo2;

import javax.management.Notification;

/**
 *  ApplicationContext 是一个单例类，负责 Alert 的创建、
 *  组装（alertRule 和 notification 的依赖注入）、初始化（添加 handlers）工作。
 *
 *  第一处改动是：在 ApiStatInfo 类中添加新的属性 timeoutCount。
 *  第二处改动是：添加新的 TimeoutAlertHander 类。
 *  第三处改动是：在 ApplicationContext 类的 initializeBeans() 方法中，
 *              往 alert 对象中注册新的 timeoutAlertHandler。
 *  第四处改动是：在使用 Alert 类的时候，需要给 check() 函数的入参 apiStatInfo 对象设置 timeoutCount 的值
 * @author pengjie_yao
 * @date 2019/12/18 16:45
 */

public class ApplicationContext {
    private AlertRule alertRule;
    private Notification notification;
    private Alert alert;

    public void initializeBeans() {
        alertRule = new AlertRule(/*.省略参数.*/); //省略一些初始化代码
        notification = new Notification(/*.省略参数.*/); //省略一些初始化代码
        alert = new Alert();
        alert.addAlertHandler(new TpsAlertHandler(alertRule, notification));
        alert.addAlertHandler(new ErrorAlertHandler(alertRule, notification));
        // todo 改动3 注册hanlder
        alert.addAlertHandler(new TimeoutAlertHandler(alertRule, notification));
    }
    public Alert getAlert() { return alert; }

    // 饿汉式单例
    private static final ApplicationContext instance = new ApplicationContext();
    private ApplicationContext() {
        instance.initializeBeans();
    }
    public static ApplicationContext getInstance() {
        return instance;
    }
}

