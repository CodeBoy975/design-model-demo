package com.yao.demo2.Interface;


import com.yao.demo2.entry.RequestInfo;

import java.util.List;
import java.util.Map;

/**
 * 该接口负责原始数据存储
 *
 * @author pengjie_yao
 * @date 2020/3/13 14:31
 */
public interface MetricsStorage {

    /**
     * 保存请求信息
     *
     * @param requestInfo
     */
    void saveRequestInfo(RequestInfo requestInfo);

    /**
     * 获取接口的请求信息
     *
     * @param apiName
     * @param startTimeInMillis
     * @param endTimeInMillis
     * @return
     */
    List<RequestInfo> getRequestInfos(String apiName, long startTimeInMillis, long endTimeInMillis);

    /**
     *  获取 请求信息
     * @param startTimeInMillis
     * @param endTimeInMillis
     * @return
     */
    Map<String, List<RequestInfo>> getRequestInfos(long startTimeInMillis, long endTimeInMillis);

}
