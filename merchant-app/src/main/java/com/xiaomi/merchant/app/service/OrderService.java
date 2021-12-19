package com.xiaomi.merchant.app.service;

import com.xiaomi.merchant.domain.client.CashierDeskClient;
import com.xiaomi.merchant.domain.constant.OrderStatus;
import com.xiaomi.merchant.domain.entity.PayOrder;
import com.xiaomi.merchant.domain.vo.Goods;
import com.xiaomi.merchant.domain.entity.Order;
import com.xiaomi.merchant.domain.entity.User;
import com.xiaomi.merchant.domain.repository.GoodsRepository;
import com.xiaomi.merchant.domain.repository.OrderRepository;
import com.xiaomi.merchant.domain.repository.UserRepository;
import com.xiaomi.merchant.domain.service.OrderPayService;
import com.xiaomi.merchant.domain.vo.OrderLine;
import com.xiaomi.merchant.domain.vo.PayInfo;
import com.xiaomi.merchant.domain.vo.PayReqParam;
import com.xiaomi.merchant.infastructure.SerialNumGenerator;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;

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
        List<OrderLine> orderLines = new ArrayList<>();
        orderDetail.forEach((goodsId,count)->{
            OrderLine orderLine = new OrderLine();
            orderLine.setGoodsId(goodsId);
            orderLine.setGoodsCount(count);
            orderLines.add(orderLine);
        });
        Order order = new Order();
        order.setOrderId(SerialNumGenerator.getNextSerialNum("order"));
        order.setOwnerId(userId);
        order.setGoodsDetail(orderLines);
        order.place(orderRepository,goodsRepository);
        return order;
    }



    /**
     * 支付申请
     * @param userId
     * @param orderId
     * @param payType 支付方式
     * @return
     */
    public PayInfo payApply(String userId, String orderId, String payType){
        Order order = Order.load(orderRepository,orderId);
        User user = userRepository.find(userId);
        PayOrder payOrder = new PayOrder();
        payOrder.setPayOrderId(SerialNumGenerator.getNextSerialNum("payOrder"));
        PayReqParam payParam = new PayReqParam();
        payParam.setPayType(payType);
        payOrder.setPayParam(payParam);
        orderPayService.payApply(payType, user, order,payOrder);
        return cashierDeskClient.payApply(payOrder).getPayInfo();
    }






}
