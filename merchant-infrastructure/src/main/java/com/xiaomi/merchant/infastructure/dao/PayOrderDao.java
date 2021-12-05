package com.xiaomi.merchant.infastructure.dao;

import com.xiaomi.merchant.domain.entity.PayOrder;
import com.xiaomi.merchant.infastructure.dataobj.OrderDo;
import com.xiaomi.merchant.infastructure.dataobj.PayOrderDo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PayOrderDao {

    @Insert("insert into pay_order values(#{order.payOrderId},#{order.orderId},#{order.payOrderStatus},#{order.payAmount},#{order.payTime},#{order.createTime})")
    int insert(@Param("order")PayOrderDo payOrderDo);

    @Select("select * from pay_order where order_id = #{orderId}")
    List<PayOrderDo> selectByOrderId(@Param("orderId") String orderId);

}
