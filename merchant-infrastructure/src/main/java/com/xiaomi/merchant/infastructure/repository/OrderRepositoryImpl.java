package com.xiaomi.merchant.infastructure.repository;

import com.google.gson.Gson;
import com.xiaomi.merchant.domain.entity.Order;
import com.xiaomi.merchant.domain.entity.PayOrder;
import com.xiaomi.merchant.domain.repository.OrderRepository;
import com.xiaomi.merchant.infastructure.convert.OrderConverter;
import com.xiaomi.merchant.infastructure.dao.OrderDao;
import com.xiaomi.merchant.infastructure.dao.PayOrderDao;
import com.xiaomi.merchant.infastructure.dataobj.OrderDo;
import com.xiaomi.merchant.infastructure.dataobj.PayOrderDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private PayOrderDao payOrderDao;

    @Override
    public void save(Order order) {
        OrderDo orderDo = orderDao.selectById(order.getOrderId());
        if(orderDo == null){
            orderDo = OrderConverter.convertToDo(order);
            orderDo.setCreateTime(new Date());
            orderDao.insert(orderDo);
        }
    }

    @Override
    public void savePayOrder(Order order) {

    }

    @Override
    public Order find(String orderId) {
        List<PayOrderDo> payOrders = payOrderDao.selectByOrderId(orderId);
        OrderDo orderDo = orderDao.selectById(orderId);
        //OrderConverter.convertToDo()
        return null;
    }
}
