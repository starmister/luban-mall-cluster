package com.luban.mall.config;

import com.luban.mall.component.zklock.ZKLock;
import com.luban.mall.component.zklock.ZKLockImpl;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ZkCuratorConfig {
    @Value("${zk.curator.retryCount}")
    private int retryCount;
    @Value("${zk.curator.elapsedTimeMs}")
    private int elapsedTimeMs;
    @Value("${zk.curator.connectUrl}")
    private String connectUrl;
    @Value("${zk.curator.sessionTimeOutMs}")
    private int sessionTimeOutMs;
    @Value("${zk.curator.connectionTimeOutMs}")
    private int connectionTimeOutMs;

    /**
     * 初始化客户端
     * @return
     */
    @Bean(initMethod = "start",destroyMethod = "close")
    public CuratorFramework curatorFramework(){
        return CuratorFrameworkFactory.newClient(
                connectUrl
                , sessionTimeOutMs
                , connectionTimeOutMs
                , new RetryNTimes(retryCount,elapsedTimeMs));
    }

    @Bean
    public ZKLock zkLock(){
        return new ZKLockImpl();
    }
}
