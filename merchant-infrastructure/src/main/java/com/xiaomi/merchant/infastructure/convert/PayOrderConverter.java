package com.xiaomi.merchant.infastructure.convert;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.xiaomi.merchant.domain.entity.PayOrder;
import com.xiaomi.merchant.infastructure.dataobj.PayOrderDo;

import java.util.Date;

public class PayOrderConverter {
    public static PayOrderDo convertToDo(PayOrder payOrder) {
        PayOrderDo payOrderDo = new PayOrderDo();
        payOrderDo.setOrderId(payOrder.getOrderId());
        payOrderDo.setPayTime(payOrder.getPayTime());
        payOrderDo.setPayParam(new Gson().toJson(payOrder.getPayParam()));
        payOrderDo.setPayOrderId(payOrder.getPayOrderId());
        payOrderDo.setPayOrderStatus(payOrder.getPayStatus().getCode());
        payOrderDo.setPayAmount(payOrder.getPayAmount());
        return payOrderDo;
    }
}
