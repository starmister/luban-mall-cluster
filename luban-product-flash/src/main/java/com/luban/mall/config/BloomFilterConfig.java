package com.luban.mall.config;
import com.google.common.base.Charsets;
import com.google.common.hash.Funnel;
import com.luban.common.constant.RedisKeyPrefixConst;
import com.luban.mall.component.BloomRedisService;
import com.luban.mall.service.PmsProductService;
import com.luban.mall.util.BloomFilterHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import java.util.List;

@Slf4j
@Configuration
public class BloomFilterConfig implements InitializingBean{

    @Autowired
    private PmsProductService productService;

    @Autowired
    private RedisTemplate template;

    @Bean
    public BloomFilterHelper<String> initBloomFilterHelper() {
        return new BloomFilterHelper<>((Funnel<String>) (from, into) -> into.putString(from, Charsets.UTF_8)
                .putString(from, Charsets.UTF_8), 1000000, 0.01);//误差率
    }

/**
     * 布隆过滤器bean注入
     * @return
     */

    @Bean
    public BloomRedisService bloomRedisService(){
        BloomRedisService bloomRedisService = new BloomRedisService();
        bloomRedisService.setBloomFilterHelper(initBloomFilterHelper());
        bloomRedisService.setRedisTemplate(template);
        return bloomRedisService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        List<Long> list = productService.getAllProductId();
        log.info("加载产品到布隆过滤器当中,size:{}",list.size());
        if(!CollectionUtils.isEmpty(list)){
            list.stream().forEach(item->{
              //  LocalBloomFilter.put(item);
               bloomRedisService().addByBloomFilter(RedisKeyPrefixConst.PRODUCT_REDIS_BLOOM_FILTER,item+"");
            });
        }
    }
}

