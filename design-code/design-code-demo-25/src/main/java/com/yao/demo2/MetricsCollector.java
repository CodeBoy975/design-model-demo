package com.yao.demo2;

import com.yao.demo2.Interface.MetricsStorage;
import com.yao.demo2.entry.RequestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

/**
 * 该类主要负责API，来采集接口请求的元素数据
 *
 * @author pengjie_yao
 * @date 2020/3/13 14:30
 */
@RestController
public class MetricsCollector {

    @Autowired
    private MetricsStorage metricsStorage;

    /**
     * 依赖注入
     *
     * @param metricsStorage
     */
    public MetricsCollector(MetricsStorage metricsStorage) {
        this.metricsStorage = metricsStorage;
    }

    /**
     * 用一个函数代替版本1中的两个函数
     */
    public void recordRequest(RequestInfo requestInfo) {
        if (requestInfo == null || StringUtils.isEmpty(requestInfo.getApiName())) {
            return ;
        }
        metricsStorage.saveRequestInfo(requestInfo);
    }
}
