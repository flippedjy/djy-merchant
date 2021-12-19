package com.xiaomi.merchant.infastructure.convert;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.xiaomi.merchant.domain.constant.OrderStatus;
import com.xiaomi.merchant.domain.entity.Order;
import com.xiaomi.merchant.domain.entity.PayOrder;
import com.xiaomi.merchant.domain.entity.User;
import com.xiaomi.merchant.domain.vo.OrderLine;
import com.xiaomi.merchant.infastructure.dataobj.OrderDo;
import com.xiaomi.merchant.infastructure.dataobj.PayOrderDo;
import org.springframework.boot.json.GsonJsonParser;

import java.util.List;


public class OrderConverter {

    public static OrderDo convertToDo(Order order){
        OrderDo orderDo = new OrderDo();
        orderDo.setOrderId(order.getOrderId());
        orderDo.setOrderStatus(Integer.parseInt(order.getOrderStatus().getCode()));
        orderDo.setDeliveryAddress(order.getAddress());
        orderDo.setExpireTime(order.getOrderExpireDate());
        orderDo.setOwnerId(order.getOwnerId());
        orderDo.setOrderDetail(new GsonBuilder().create().toJson(order.getGoodsDetail()));
        return orderDo;
    }

    public static Order convertToOrder(OrderDo orderDo, List<PayOrderDo> payOrders, User user) {
        Order order = new Order();
        order.setOrderExpireDate(orderDo.getExpireTime());
        order.setOrderId(orderDo.getOrderId());
        order.setGoodsDetail(new Gson().fromJson(orderDo.getOrderDetail(),new TypeToken<List<OrderLine>>(){}.getType()));
        order.setOrderStatus(OrderStatus.findByCode(""+orderDo.getOrderStatus()));
        order.setOrderPlaceDate(orderDo.getCreateTime());
        order.setAddress(orderDo.getDeliveryAddress());
        order.setOwnerId(order.getOwnerId());
        return order;
    }

}
