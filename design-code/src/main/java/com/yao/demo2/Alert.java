package com.yao.demo2;

import javax.management.Notification;
import java.util.ArrayList;
import java.util.List;

/**
 *  警告類
 * @author pengjie_yao
 * @date 2019/12/18 16:27
 */
public class Alert {

    private List<AlertHandler> alertHandlers = new ArrayList<>();


    public void addAlertHandler(AlertHandler alertHandler) {
        this.alertHandlers.add(alertHandler);
    }


    /**
     *  报警函数
     * @param apiStatInfo
     */
    public void check(ApiStatInfo apiStatInfo) {
        for (AlertHandler handler : alertHandlers) {
            handler.check(apiStatInfo);
        }
    }
}
