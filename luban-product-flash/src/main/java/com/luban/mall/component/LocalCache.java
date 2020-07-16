package com.luban.mall.component;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.luban.mall.domain.PmsProductParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;


@Slf4j
@Component
public class LocalCache {

    private Cache<String,PmsProductParam> localCache = null;

    @PostConstruct
    private void init(){
        localCache = CacheBuilder.newBuilder()
                //设置本地缓存容器的初始容量
                .initialCapacity(10)
                //设置本地缓存的最大容量
                .maximumSize(500)
                //设置写缓存后多少秒过期
                .expireAfterWrite(60, TimeUnit.SECONDS).build();
    }


    public void setLocalCache(String key,PmsProductParam object){
        localCache.put(key,object);
    }

    public PmsProductParam get(String key){
       return localCache.getIfPresent(key);
    }

}
