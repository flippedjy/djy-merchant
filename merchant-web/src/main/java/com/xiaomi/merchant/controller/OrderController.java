package com.xiaomi.merchant.controller;


import com.xiaomi.merchant.app.service.OrderService;
import com.xiaomi.merchant.context.BusinessContext;
import com.xiaomi.merchant.domain.entity.User;
import com.xiaomi.merchant.domain.repository.UserRepository;
import com.xiaomi.merchant.domain.vo.PayReqParam;
import com.xiaomi.merchant.dto.PlaceOrderReq;
import com.xiaomi.merchant.infastructure.client.CashierDeskClientImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("order")
@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CashierDeskClientImpl cashierDeskClient;

    @PostMapping("/create")
    public Object createOrder(@RequestBody PlaceOrderReq req){

        return orderService.placeOrder(BusinessContext.getUserId(),req.getOrderDetail());
    }



    @GetMapping("/deductinfo/{payType}")
    public Object queryUserDeductInfo(@PathVariable(name="payType") String payType){

        // parseDTO
        User user = userRepository.find(BusinessContext.getUserId());
        List<PayReqParam> userDeductInfos = cashierDeskClient.queryUserDeductInfos(user.getXiaomiId(),payType);
        return userDeductInfos;
    }






}
