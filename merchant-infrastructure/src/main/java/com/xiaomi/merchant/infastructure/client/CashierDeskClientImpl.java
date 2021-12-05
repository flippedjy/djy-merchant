package com.xiaomi.merchant.infastructure.client;

import com.xiaomi.merchant.domain.client.CashierDeskClient;
import com.xiaomi.merchant.domain.entity.Order;
import com.xiaomi.merchant.domain.service.PaymentService;
import com.xiaomi.merchant.domain.vo.PayReqParam;
import com.xiaomi.merchant.domain.vo.PayResp;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CashierDeskClientImpl implements InitializingBean,CashierDeskClient{

    @Autowired
    List<PaymentService> paymentServices;

    private Map<String,PaymentService > paymentServicesMap = new HashMap<>();


    public List<PayReqParam> queryUserDeductInfos(String xiaomiId, String payType) {
        return null;
    }

    public PayReqParam queryUserDeductInfo(String userId, String deductId){
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if(CollectionUtils.isEmpty(paymentServices)){
            return;
        }
        paymentServices.stream().forEach(paymentService -> paymentServicesMap.put(paymentService.getPayType(),paymentService));
    }
    @Override
    public PayResp payApply(Order order) {
        PaymentService paymentService = paymentServicesMap.get(order.getPayOrders().get(0).getPayParam().getPayType());
        Assert.notNull(paymentService,"unsupport pay type");
        PayResp payResp = paymentService.payApply(order);
        return payResp;
    }
    @Override
    public PayResp queryOrderPayStatus(String orderId){
        return null;
    }
}
