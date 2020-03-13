package com.yao.demo1;

import com.google.gson.Gson;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 指标类：用于存储： 响应时间、访问时间等信息
 *
 * @author pengjie_yao
 * @date 2020/3/12 10:23
 */
@Data
public class Metrics {

    /**
     * 响应时间的存储
     */
    private Map<String, List<Double>> responseTimes = new HashMap<>();

    /**
     * 访问时间
     */
    private Map<String, List<Double>> timestamps = new HashMap<>();

    /**
     * 定时任务的线程池
     */
    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();


    /**
     * 记录响应时间
     *
     * @param apiName
     * @param responseTime
     */
    public void recordResponseTime(String apiName, double responseTime) {
        responseTimes.putIfAbsent(apiName, new ArrayList<>());
        responseTimes.get(apiName).add(responseTime);
    }

    /**
     * 记录访问时间
     *
     * @param apiName
     * @param timestamp
     */
    public void recordTimesstamp(String apiName, double timestamp) {
        timestamps.putIfAbsent(apiName, new ArrayList<>());
        timestamps.get(apiName).add(timestamp);
    }

    /**
     * 以固定的频率统计数据并输出结果
     * <p>
     * Gson提供了fromJson() 和toJson() 两个直接用于解析和生成的方法，前者实现反序列化，后者实现了序列化；同时每个方法都提供了重载方法
     *
     * @param period
     * @param unit
     */
    public void startRepeatedReport(long period, TimeUnit unit) {
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                Map<String, Map<String, Double>> stats = new HashMap<>();
                // 获取响应时间接口最大值
                for (Map.Entry<String, List<Double>> entry : responseTimes.entrySet()) {
                    String apiName = entry.getKey();
                    List<Double> apiRespTimes = entry.getValue();
                    stats.putIfAbsent(apiName, new HashMap<>());
                    stats.get(apiName).put("max", max(apiRespTimes));
                    stats.get(apiName).put("avg", avg(apiRespTimes));
                }

                // 获取访问时间
                for (Map.Entry<String, List<Double>> entry : timestamps.entrySet()) {
                    String apiName = entry.getKey();
                    List<Double> apiTimestamps = entry.getValue();
                    stats.putIfAbsent(apiName, new HashMap<>());
                    stats.get(apiName).put("count", (double) apiTimestamps.size());
                }
                // json格式输出
                System.out.println(gson.toJson(stats));
            }
        }, 0, period, unit);
    }


    /**
     * 计算最大值
     *
     * @param times
     * @return
     */
    private double max(List<Double> times) {
        if (!CollectionUtils.isEmpty(times)) {
            double max = times.get(0);
            for (Double entry : times) {
                if (max < entry) {
                    max = entry;
                }
            }
            return max;
        }

        return -1;
    }

    /**
     * 计算平均值
     *
     * @param times
     * @return
     */
    private double avg(List<Double> times) {
        if (!CollectionUtils.isEmpty(times)) {
            double sum = 0;
            for (Double entry : times) {
                sum += entry;
            }
            return sum / times.size();
        }

        return -1;
    }
}
