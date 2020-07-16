package com.luban.config;

import com.luban.Component.LubanRestTemplate;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RibbonConfig {

    /**
     * 方法实现说明:原生的RestTemplate +@LB不行 因为在
     * InitializingBean方法执行前我们的RestTemplate还没有被增强
     * 需要自己改写RestTemplate
     */
    @Bean
    public LubanRestTemplate restTemplate(DiscoveryClient discoveryClient) {
        return new LubanRestTemplate(discoveryClient);
    }
}
