package com.xiaomi.merchant.infastructure.repository;

import com.google.gson.Gson;
import com.xiaomi.merchant.domain.entity.Order;
import com.xiaomi.merchant.domain.entity.PayOrder;
import com.xiaomi.merchant.domain.entity.User;
import com.xiaomi.merchant.domain.repository.OrderRepository;
import com.xiaomi.merchant.domain.repository.UserRepository;
import com.xiaomi.merchant.infastructure.convert.OrderConverter;
import com.xiaomi.merchant.infastructure.dao.OrderDao;
import com.xiaomi.merchant.infastructure.dao.PayOrderDao;
import com.xiaomi.merchant.infastructure.dataobj.OrderDo;
import com.xiaomi.merchant.infastructure.dataobj.PayOrderDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private PayOrderDao payOrderDao;

    @Autowired
    private UserRepository userRepository;



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
    public Order find(String orderId) {
        List<PayOrderDo> payOrders = payOrderDao.selectByOrderId(orderId);
        OrderDo orderDo = orderDao.selectById(orderId);
        User user = userRepository.find(orderDo.getOwnerId());
        Order order = OrderConverter.convertToOrder(orderDo, payOrders, user);
        return order;
    }
}
