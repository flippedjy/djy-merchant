package com.xiaomi.merchant.infastructure.dao;

import com.xiaomi.merchant.infastructure.dataobj.OrderDo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface OrderDao {

    @Insert("insert into `order` values(#{order.orderId},#{order.orderStatus},#{order.ownerId},#{order.createTime},#{order.expireTime}," +
            "#{order.deliveryAddress},#{order.orderDetail},#{order.payOrderId})")
    int insert(@Param("order") OrderDo order);

    @Select("select * from `order` where order_id=#{id}")
    OrderDo selectById(@Param("id") String id);

    @Update("update order set pay_order_id=#{order.payOrderId},order_status=#{order.orderStatus}")
    int update(@Param("order") OrderDo order);
}
