package com.yao.demo2;

import com.yao.demo2.Interface.MetricsStorage;
import com.yao.demo2.entry.RequestInfo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * redis的方式存储
 *
 * @author pengjie_yao
 * @date 2020/3/13 14:32
 */
@Component
public class RedisMetricsStorage implements MetricsStorage {
    // 暂时用map来代替
    /**
     * 响应时间的存储
     */
    private Map<String, List<RequestInfo>> responseTimes = new HashMap<>();

    /**
     * 访问时间
     */
    private Map<String, List<RequestInfo>> timestamps = new HashMap<>();

    @Override
    public void saveRequestInfo(RequestInfo requestInfo) {

        responseTimes.putIfAbsent(requestInfo.getApiName(), new ArrayList<>());
        responseTimes.get(requestInfo.getApiName()).add(requestInfo);

        timestamps.putIfAbsent(requestInfo.getApiName(), new ArrayList<>());
        timestamps.get(requestInfo.getApiName()).add(requestInfo);
    }

    @Override
    public List<RequestInfo> getRequestInfos(String apiName, long startTimeInMillis, long endTimeInMillis) {
        return responseTimes.get(apiName);
    }

    @Override
    public Map<String, List<RequestInfo>> getRequestInfos(long startTimeInMillis, long endTimeInMillis) {

        Map<String, List<RequestInfo>> demo = new HashMap<>();
        List<RequestInfo> list = new ArrayList<>();
        RequestInfo requestInfo = new RequestInfo("register", 123, 10234);
        list.add(requestInfo);
        demo.put("register", list);
        return demo;
    }
}
