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
    private String payOrderStatus;

    //支付金额
    private Long payAmount;

    //创建时间
    private Date createTime;

    private Date payTime;

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

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

    public String getPayOrderStatus() {
        return payOrderStatus;
    }

    public void setPayOrderStatus(String payOrderStatus) {
        this.payOrderStatus = payOrderStatus;
    }

    public Long getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Long payAmount) {
        this.payAmount = payAmount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
