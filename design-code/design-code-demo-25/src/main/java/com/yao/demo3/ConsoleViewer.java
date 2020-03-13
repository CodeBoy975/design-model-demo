package com.yao.demo3;

import com.google.gson.Gson;
import com.yao.demo2.entry.RequestStat;

import java.util.Map;

/**
 * 控制台输出方式
 *
 * @author pengjie_yao
 * @date 2020/3/13 17:25
 */
public class ConsoleViewer implements StatViewer {

    @Override
    public void output(Map<String, RequestStat> requestStats, long startTimeInMillis, long endTimeInMills) {
        System.out.println("Time Span: [" + startTimeInMillis + ", " + endTimeInMills + "]");
        Gson gson = new Gson();
        System.out.println(gson.toJson(requestStats));
    }
}
