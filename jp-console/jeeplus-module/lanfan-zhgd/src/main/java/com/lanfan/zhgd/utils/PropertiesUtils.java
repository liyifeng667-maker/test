package com.lanfan.zhgd.utils;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@ConfigurationProperties(prefix = "ocr")
@Data
@Component
/**
 * 配置参数获取类
 */
public class PropertiesUtils implements InitializingBean {
    public static Map<String, String> externalUrl;//外部url
    public static Map<String,Integer> threadPool;//各线程池数量
    public static Map<String,String> tokenValue;//接口token
    public static Map<String,String> location;//接口token



    public Map<String, String> getLocation() {
        return location;
    }
    public void setLocation(Map<String, String> location) {
        this.location = location;
    }
    public Map<String, String> getExternalUrl() {
        return externalUrl;
    }

    public void setExternalUrl(Map<String, String> externalUrl) {
        this.externalUrl = externalUrl;
    }

    public Map<String, Integer> getThreadPool() {
        return threadPool;
    }

    public void setThreadPool(Map<String, Integer> threadPool) {
        this.threadPool = threadPool;
    }

    public Map<String, String> getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(Map<String, String> tokenValue) {
        this.tokenValue = tokenValue;
    }
    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
