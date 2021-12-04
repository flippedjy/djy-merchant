package com.xiaomi.merchant.app.service;

import com.xiaomi.merchant.domain.client.CashierDeskClient;
import com.xiaomi.merchant.domain.entity.Order;
import com.xiaomi.merchant.domain.entity.User;
import com.xiaomi.merchant.domain.repository.GoodsRepository;
import com.xiaomi.merchant.domain.repository.OrderRepository;
import com.xiaomi.merchant.domain.repository.UserRepository;
import com.xiaomi.merchant.domain.service.OrderPayService;
import com.xiaomi.merchant.infastructure.SerialNumGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Map;

@Service
public class OrderService {

    private static final String ORDER_SERIAL_NAME = "order";


    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    GoodsRepository goodsRepository;

    @Autowired
    OrderPayService orderPayService;

    @Autowired
    CashierDeskClient cashierDeskClient;

    /**
     * 下单
     * @param userId
     * @param orderDetail
     * @return
     */
    public Order placeOrder(String userId, Map<String,Integer> orderDetail){
        User user = userRepository.find(userId);
        Order order = Order.newBuilder()
                .orderId(SerialNumGenerator.getNextSerialNum(ORDER_SERIAL_NAME))
                .owner(user)
                .addGoods(orderDetail)
                .build();
        order.place(orderRepository,goodsRepository);
        return order;
    }

    /**
     * 支付申请
     * @param userId
     * @param orderId
     * @param deductId
     * @return
     */
    public Order payApply(String userId,String orderId,String deductId){
        Order order = orderRepository.find(orderId);
        User user = userRepository.find(userId);
        orderPayService.payApply(SerialNumGenerator.getNextSerialNum("payOrder"),user,order,deductId);
        return order;
    }

    /**
     * 同步支付状态
     * @param userId
     * @param orderId
     * @return
     */
    public Order syncPayStatus(String userId,String orderId){
        Order order = orderRepository.find(orderId);
        Assert.isTrue(order.getOwnerId().equals(userId),"operation denied");
        order.syncPayStatus(cashierDeskClient);
        return order;
    }




}
