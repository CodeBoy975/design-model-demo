package com.yao.demo3;

import com.yao.demo2.entry.EmailSender;
import com.yao.demo2.entry.RequestStat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 邮箱显示方式
 *
 * @author pengjie_yao
 * @date 2020/3/13 17:26
 */
public class EmailViewer implements StatViewer {
    private EmailSender emailSender;


    private List<String> toAddresses = new ArrayList<>();

    public EmailViewer() {
        this.emailSender = new EmailSender(/*省略参数*/);
    }

    public EmailViewer(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void addToAddress(String address) {
        toAddresses.add(address);
    }

    @Override
    public void output(
            Map<String, RequestStat> requestStats, long startTimeInMillis, long endTimeInMills) {
        // format the requestStats to HTML style.
        // send it to email toAddresses.
    }
}
