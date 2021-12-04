package com.xiaomi.merchant.infastructure.dao;

import com.xiaomi.merchant.infastructure.dataobj.GoodsDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface GoodsDao {

    @Select("select * from goods where id=#{id}")
    GoodsDo selectById(@Param("id") String id);
}
