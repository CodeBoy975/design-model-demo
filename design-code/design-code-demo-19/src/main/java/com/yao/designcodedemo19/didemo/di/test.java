package com.yao.designcodedemo19.didemo.di;


/**
 * @author pengjie_yao
 * @date 2019/12/23 18:03
 */
public class test {

    public static void main(String[] args) {

        // 依赖注入的方式
        MessageSender messageSender = new MessageSender();
        Notification notification = new Notification(messageSender);

    }
}
