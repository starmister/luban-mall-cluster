package com.luban.mall.component;

import com.google.common.base.Preconditions;
import com.luban.mall.util.BloomFilterHelper;
import org.springframework.data.redis.core.RedisTemplate;

public class BloomRedisService {

    private RedisTemplate<String, Object> redisTemplate;

    private BloomFilterHelper bloomFilterHelper;

    public void setBloomFilterHelper(BloomFilterHelper bloomFilterHelper) {
        this.bloomFilterHelper = bloomFilterHelper;
    }

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 根据给定的布隆过滤器添加值
     */
    public <T> void addByBloomFilter(String key, T value) {
        Preconditions.checkArgument(bloomFilterHelper != null, "bloomFilterHelper不能为空");
        int[] offset = bloomFilterHelper.murmurHashOffset(value);
        for (int i : offset) {
            redisTemplate.opsForValue().setBit(key, i, true);
        }
    }

    /**
     * 根据给定的布隆过滤器判断值是否存在
     */
    public <T> boolean includeByBloomFilter(String key, T value) {
        Preconditions.checkArgument(bloomFilterHelper != null, "bloomFilterHelper不能为空");
        int[] offset = bloomFilterHelper.murmurHashOffset(value);
        for (int i : offset) {
            if (!redisTemplate.opsForValue().getBit(key, i)) {
                return false;
            }
        }
        return true;
    }
}
