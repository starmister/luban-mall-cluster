package com.luban.mall.domain;

import com.luban.model.PmsProduct;
import com.luban.model.PmsProductAttribute;
import com.luban.model.PmsSkuStock;

import java.util.List;

/**
 * 购物车中选择规格的商品信息
 * Created by macro on 2018/8/2.
 */
public class CartProduct extends PmsProduct {
    private List<PmsProductAttribute> productAttributeList;
    private List<PmsSkuStock> skuStockList;

    public List<PmsProductAttribute> getProductAttributeList() {
        return productAttributeList;
    }

    public void setProductAttributeList(List<PmsProductAttribute> productAttributeList) {
        this.productAttributeList = productAttributeList;
    }

    public List<PmsSkuStock> getSkuStockList() {
        return skuStockList;
    }

    public void setSkuStockList(List<PmsSkuStock> skuStockList) {
        this.skuStockList = skuStockList;
    }
}
