package com.xiaomi.merchant.infastructure.dao;

import com.xiaomi.merchant.infastructure.dataobj.UserDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {

    @Select("select * from user where user_id = #{userId}")
    UserDo selectByUserId(String userId);
}
