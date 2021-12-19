package com.xiaomi.merchant.domain.service;

import com.xiaomi.merchant.domain.client.CashierDeskClient;
import com.xiaomi.merchant.domain.entity.Order;
import com.xiaomi.merchant.domain.entity.PayOrder;
import com.xiaomi.merchant.domain.entity.User;
import com.xiaomi.merchant.domain.repository.OrderRepository;
import com.xiaomi.merchant.domain.repository.PayOrderRepository;
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

    @Autowired
    PayOrderRepository payOrderRepository;


    public void payApply(String payType, User user, Order order, PayOrder payOrder) {
        payOrder.setPayAmount(order.getTotalPrice());
        payOrder.setOrderId(order.getOrderId());
        payOrder.setOwnerId(user.getUserId());
        payOrder.create(payOrderRepository);

    }


}
