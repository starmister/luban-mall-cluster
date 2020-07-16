package com.luban.mall.dao;


import com.luban.mall.domain.CartProduct;
import com.luban.mall.domain.PmsProductParam;
import com.luban.mall.domain.PromotionProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 前台系统自定义商品Dao
 */
public interface PortalProductDao {
    CartProduct getCartProduct(@Param("id") Long id);
    List<PromotionProduct> getPromotionProductList(@Param("ids") List<Long> ids);

    /**
     * 获取商品详情信息
     * @param id 产品ID
     */
    PmsProductParam getProductInfo(@Param("id") Long id);

    /**
     * 查找出所有的产品ID
     * @return
     */
    List<Long> getAllProductId();
}
