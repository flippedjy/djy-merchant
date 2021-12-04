package com.xiaomi.merchant.infastructure.dao;

import com.xiaomi.merchant.infastructure.dataobj.GoodsDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GoodsDao {

    @Select("select * from goods where id=#{id}")
    GoodsDo selectById(@Param("id") String id);

    @Select({
            "<script>",
            "select",
            "*",
            "from goods",
            "where id in",
            "<foreach collection='ids' item='id' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"
    }
            )
    List<GoodsDo> selectInIds(@Param("ids") List<String> ids);
}
