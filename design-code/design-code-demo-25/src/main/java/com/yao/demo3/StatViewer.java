package com.yao.demo3;

import com.yao.demo2.entry.RequestStat;

import java.util.Map;

/**
 * 显示方式的接口类型
 *
 * @author pengjie_yao
 * @date 2020/3/13 17:25
 */
public interface StatViewer {

    /**
     * 输出
     *
     * @param requestStats
     * @param startTimeInMillis
     * @param endTimeInMills
     */
    void output(Map<String, RequestStat> requestStats, long startTimeInMillis, long endTimeInMills);
}
