package com.yao.designcodedemo19.didemo.di;

import com.yao.designcodedemo19.didemo.di.MessageSender;

/**
 *  依赖注入的方式
 * @author pengjie_yao
 * @date 2019/12/23 18:01
 */
public class Notification {

    private MessageSender messageSender;

    public Notification(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void sendMessage(String cellPhone, String message) {
        this.messageSender.send(cellPhone,message);
    }
}
