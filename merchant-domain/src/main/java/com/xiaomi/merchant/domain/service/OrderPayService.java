package com.xiaomi.merchant.domain.service;

import com.xiaomi.merchant.domain.client.CashierDeskClient;
import com.xiaomi.merchant.domain.entity.Order;
import com.xiaomi.merchant.domain.entity.User;
import com.xiaomi.merchant.domain.repository.OrderRepository;
import com.xiaomi.merchant.domain.vo.PayReqParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Arrays;

@Service
public class OrderPayService {

    @Autowired
    CashierDeskClient cashierDeskClient;

    @Autowired
    OrderRepository orderRepository;



    /**
     * 支付
     * @param user
     * @param order
     * @param deductId
     */
    public void payApply(String payOrderId,User user, Order order, String deductId) {
        Assert.isTrue(StringUtils.equals(user.getUserId(),order.getOwnerId()),"can't pay for other people's order");
        PayReqParam payReqParam = cashierDeskClient.queryUserDeductInfo(user.getUserId(), deductId);
        order.payApply(payOrderId,orderRepository,payReqParam,cashierDeskClient);

    }



}
