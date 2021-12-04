package com.xiaomi.merchant.domain.service;

import com.xiaomi.merchant.domain.entity.Order;
import com.xiaomi.merchant.domain.vo.PayResp;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    public String getPayType() {
        return "";
    }

    @Override
    public PayResp payApply(Order order) {
        return null;
    }
}
