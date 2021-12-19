package com.xiaomi.merchant.domain.entity;

import com.xiaomi.merchant.domain.client.CashierDeskClient;
import com.xiaomi.merchant.domain.constant.PayStatus;
import com.xiaomi.merchant.domain.repository.PayOrderRepository;
import com.xiaomi.merchant.domain.vo.PayReqParam;
import com.xiaomi.merchant.domain.vo.PayResp;
import org.springframework.util.Assert;

import java.util.Date;

public class PayOrder {

    //归属用户
    private String ownerId;

    //订单号
    private String orderId;

    //支付订单号
    private String payOrderId;

    //支付金额
    private Long payAmount;

    //支付请求参数
    private PayReqParam payParam;

    //支付之间
    private Date payTime;

    //支付状态
    private PayStatus payStatus;

    //支付结果参数
    private PayResp payResp;

    //支付订单已被创建
    private boolean created;

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Long payAmount) {
        this.payAmount = payAmount;
    }

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

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public void create(PayOrderRepository payOrderRepository) {
        Assert.notNull(this.orderId,"被支付的订单号不能为空");
        Assert.notNull(this.ownerId,"支付订单必须指定支付人");
        this.payStatus = PayStatus.INIT;
        payOrderRepository.save(this);
        this.created = true;
    }


}
