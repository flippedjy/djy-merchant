package com.xiaomi.merchant.domain.vo;


public class OrderLine {

    private String goodsId;

    private long goodsPrice;

    private int goodsCount;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public long getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(long goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public int getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }
}
