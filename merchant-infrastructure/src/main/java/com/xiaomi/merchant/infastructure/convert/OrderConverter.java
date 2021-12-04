package com.xiaomi.merchant.infastructure.convert;

import com.google.gson.GsonBuilder;
import com.xiaomi.merchant.domain.entity.Order;
import com.xiaomi.merchant.domain.entity.PayOrder;
import com.xiaomi.merchant.infastructure.dataobj.OrderDo;
import org.springframework.boot.json.GsonJsonParser;

import java.util.List;


public class OrderConverter {

    public static OrderDo convertToDo(Order order){
        OrderDo orderDo = new OrderDo();
        orderDo.setOrderId(order.getOrderId());
        orderDo.setOrderStatus(Integer.parseInt(order.getOrderStatus().getCode()));
        orderDo.setDeliveryAddress(order.getAddress());
        orderDo.setExpireTime(order.getOrderExpireDate());
        orderDo.setOrderDetail(new GsonBuilder().create().toJson(order.getGoodsDetail()));
        return orderDo;
    }

    public static Order convertToOrder(OrderDo order){
        order
    }

}
