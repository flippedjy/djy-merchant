package com.xiaomi.merchant.domain.client;

import com.xiaomi.merchant.domain.entity.Order;
import com.xiaomi.merchant.domain.vo.PayReqParam;
import com.xiaomi.merchant.domain.vo.PayResp;

public interface CashierDeskClient {

    PayResp payApply(Order order);

    PayResp queryOrderPayStatus(String orderId);

    PayReqParam queryUserDeductInfo(String userId, String deductId);
}
