package com.xiaomi.merchant.domain.repository;

import com.xiaomi.merchant.domain.entity.Order;

public interface OrderRepository {

    void save(Order order);


    Order find(String orderId);
}
