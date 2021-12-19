package com.xiaomi.merchant.domain.repository;

import com.xiaomi.merchant.domain.entity.PayOrder;

public interface PayOrderRepository {

    void save(PayOrder payOrder);
}
