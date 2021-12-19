package com.xiaomi.merchant.domain.client;

import com.xiaomi.merchant.domain.entity.Order;
import com.xiaomi.merchant.domain.entity.PayOrder;
import com.xiaomi.merchant.domain.vo.PayReqParam;
import com.xiaomi.merchant.domain.vo.PayResp;

public interface CashierDeskClient {

    PayResp payApply(PayOrder payOrder);

    PayResp queryOrderPayStatus(String orderId);

    PayReqParam queryUserDeductInfo(String userId, String deductId);
}
