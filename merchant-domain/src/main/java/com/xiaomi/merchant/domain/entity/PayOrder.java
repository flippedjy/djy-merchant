package com.xiaomi.merchant.domain.entity;

import com.xiaomi.merchant.domain.constant.PayStatus;
import com.xiaomi.merchant.domain.vo.PayReqParam;
import com.xiaomi.merchant.domain.vo.PayResp;

public class PayOrder {
    //支付订单号
    private String payOrderId;

    //支付请求参数
    private PayReqParam payParam;

    //支付状态
    private PayStatus payStatus;

    //支付结果参数
    private PayResp payResp;


    public String getPayOrderId() {
        return payOrderId;
    }

    public void setPayOrderId(String payOrderId) {
        this.payOrderId = payOrderId;
    }

    public PayReqParam getPayParam() {
        return payParam;
    }

    public void setPayParam(PayReqParam payParam) {
        this.payParam = payParam;
    }

    public PayStatus getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(PayStatus payStatus) {
        this.payStatus = payStatus;
    }

    public PayResp getPayResp() {
        return payResp;
    }

    public void setPayResp(PayResp payResp) {
        this.payResp = payResp;
    }

    public PayOrder(String payOrderId, PayReqParam payParam) {
        this.payOrderId = payOrderId;
        this.payParam = payParam;
        this.payStatus = PayStatus.INIT;
    }


}
