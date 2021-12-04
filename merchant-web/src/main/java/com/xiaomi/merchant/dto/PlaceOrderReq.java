package com.xiaomi.merchant.dto;

import java.util.Map;

public class PlaceOrderReq {

    /**
     * 地址
     */
    private String address;

    /**
     * 订单详情
     */
    private Map<String,Integer> orderDetail;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Map<String, Integer> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(Map<String, Integer> orderDetail) {
        this.orderDetail = orderDetail;
    }
}
