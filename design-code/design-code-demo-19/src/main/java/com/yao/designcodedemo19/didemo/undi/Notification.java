package com.yao.designcodedemo19.didemo.undi;

/**
 * 非依赖注入的方式
 * @author pengjie_yao
 * @date 2019/12/23 18:01
 */
public class Notification {

    private MessageSender messageSender;

    public Notification() {
        // 此处有点想硬编码
        this.messageSender = new MessageSender();
    }

    public void sendMessage(String cellPhone, String message) {
        this.messageSender.send(cellPhone,message);
    }
}
