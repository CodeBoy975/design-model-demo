package com.yao.designcodedemo19.didemo.undi;

import com.yao.designcodedemo19.didemo.di.MessageSender;

/**
 * @author pengjie_yao
 * @date 2019/12/23 18:03
 */
public class test {

    public static void main(String[] args) {
        // 非依赖注入的方式，对象的创建在对象内部
        Notification notification = new Notification();
        notification.sendMessage("13112122","发送手机信息");

    }
}
