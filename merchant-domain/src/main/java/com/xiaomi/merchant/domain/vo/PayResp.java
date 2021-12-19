package com.xiaomi.merchant.domain.vo;

import com.xiaomi.merchant.domain.constant.PayStatus;


public class PayResp {
    private PayInfo payInfo;

    private PayStatus payStatus;

    public PayStatus getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(PayStatus payStatus) {
        this.payStatus = payStatus;
    }

    public PayInfo getPayInfo() {
        return payInfo;
    }

    public void setPayInfo(PayInfo payInfo) {
        this.payInfo = payInfo;
    }
}
