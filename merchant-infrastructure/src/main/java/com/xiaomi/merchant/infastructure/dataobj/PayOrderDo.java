package com.xiaomi.merchant.infastructure.dataobj;

import com.xiaomi.merchant.domain.constant.PayStatus;
import com.xiaomi.merchant.domain.vo.PayReqParam;
import com.xiaomi.merchant.domain.vo.PayResp;

import java.util.Date;

public class PayOrderDo {

    //支付订单号
    private String payOrderId;

    //所属订单号
    private String orderId;

    //支付请求参数
    private String payParam;

    //支付状态
    private String payStatus;

    //支付金额
    private Long payAmount;

    //支付结果参数
    private PayResp payResp;

    //创建时间
    private Date createTime;

    public String getPayOrderId() {
        return payOrderId;
    }

    public void setPayOrderId(String payOrderId) {
        this.payOrderId = payOrderId;
    }

    public String getPayParam() {
        return payParam;
    }

    public void setPayParam(String payParam) {
        this.payParam = payParam;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public Long getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Long payAmount) {
        this.payAmount = payAmount;
    }

    public PayResp getPayResp() {
        return payResp;
    }

    public void setPayResp(PayResp payResp) {
        this.payResp = payResp;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
