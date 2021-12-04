package com.xiaomi.merchant.infastructure.repository;

import com.xiaomi.merchant.domain.entity.Order;
import com.xiaomi.merchant.domain.repository.OrderRepository;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    @Override
    public void save(Order order) {

    }

    @Override
    public void savePayOrder(Order order) {

    }

    @Override
    public Order find(String orderId) {
        return null;
    }
}
