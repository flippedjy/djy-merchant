package com.xiaomi.merchant.controller;


import com.xiaomi.merchant.app.service.OrderService;
import com.xiaomi.merchant.context.BusinessContext;
import com.xiaomi.merchant.domain.entity.Goods;
import com.xiaomi.merchant.domain.entity.User;
import com.xiaomi.merchant.domain.repository.GoodsRepository;
import com.xiaomi.merchant.domain.repository.UserRepository;
import com.xiaomi.merchant.domain.vo.PayReqParam;
import com.xiaomi.merchant.dto.PlaceOrderReq;
import com.xiaomi.merchant.infastructure.client.CashierDeskClientImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CashierDeskClientImpl cashierDeskClient;

    @Autowired
    private GoodsRepository goodsRepository;


    @GetMapping ("/preOrder")
    public Object preOrder(@RequestParam String address ,@RequestParam List<String> goodsId,@RequestParam Long totalPrice){

        Map<String, Integer> orderDetail = goodsId.stream().collect(Collectors.toMap(String::toString, (s) -> 1));
        return orderService.placeOrder(BusinessContext.getUserId(),orderDetail,totalPrice);
    }

    @GetMapping("/createOrder")
    public Object orderPage(@RequestParam List<String> goodsIds){
        List<Goods> goods = goodsRepository.findAll(goodsIds);
        final ModelAndView modelAndView = new ModelAndView("createOrder");
        modelAndView.addObject("goods",goods);
        return modelAndView;

    }

    @GetMapping("/index")
    public Object index(){
        System.out.println("index.jsp");
        return "index";
    }



    @GetMapping("/deductinfo/{payType}")
    public Object queryUserDeductInfo(@PathVariable(name="payType") String payType){

        // parseDTO
        User user = userRepository.find(BusinessContext.getUserId());
        List<PayReqParam> userDeductInfos = cashierDeskClient.queryUserDeductInfos(user.getXiaomiId(),payType);
        return userDeductInfos;
    }






}
