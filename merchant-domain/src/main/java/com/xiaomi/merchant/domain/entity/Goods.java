package com.xiaomi.merchant.domain.entity;

import com.xiaomi.merchant.type.PriceFen;
import org.springframework.util.Assert;

public class Goods {

    private String goodsId;

    private String goodsName;

    private String goodsDesc;

    private PriceFen price;


    public Goods(String goodsId, String goodsName, String goodsDesc, long price) {
        Assert.hasText(goodsId,"goodsId must not be null");
        Assert.hasText(goodsName,"goodsName must not be null");
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.goodsDesc = goodsDesc;
        this.price = new PriceFen(price);
    }

    public String getGoodsId() {
        return goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public PriceFen getPrice() {
        return price;
    }
}
