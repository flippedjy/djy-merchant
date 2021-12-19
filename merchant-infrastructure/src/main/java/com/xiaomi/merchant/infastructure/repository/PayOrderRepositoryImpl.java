package com.xiaomi.merchant.infastructure.repository;

import com.xiaomi.merchant.domain.entity.PayOrder;
import com.xiaomi.merchant.domain.repository.PayOrderRepository;
import com.xiaomi.merchant.infastructure.convert.PayOrderConverter;
import com.xiaomi.merchant.infastructure.dao.PayOrderDao;
import com.xiaomi.merchant.infastructure.dataobj.PayOrderDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PayOrderRepositoryImpl implements PayOrderRepository {

    @Autowired
    private PayOrderDao payOrderDao;

    @Override
    public void save(PayOrder payOrder) {
        PayOrderDo payOrderDo = payOrderDao.selectByPayOrderId(payOrder.getPayOrderId());
        if(payOrderDo == null){
            PayOrderDo payOrdeoDo = PayOrderConverter.convertToDo(payOrder);
            payOrdeoDo.setCreateTime(new Date());
            payOrderDao.insert(payOrdeoDo);
        }
    }
}
