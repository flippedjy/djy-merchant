package com.xiaomi.merchant.domain.service;

import com.xiaomi.merchant.domain.entity.Order;
import com.xiaomi.merchant.domain.vo.PayReqParam;
import com.xiaomi.merchant.domain.vo.PayResp;

public interface PaymentService {

    String getPayType();

    PayResp payApply(Order order);
}
