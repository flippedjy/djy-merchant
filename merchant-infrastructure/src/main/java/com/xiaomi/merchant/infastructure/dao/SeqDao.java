package com.xiaomi.merchant.infastructure.dao;

import org.apache.ibatis.annotations.*;

@Mapper
public interface SeqDao {


    @Select("select last_idx_num from common_seq where seq_name = #{seqName}")
    Long getSeqLastIdx(String seqName);

    @Update("update common_seq set last_idx_num = #{newIdx} where seq_name=#{seqName} and last_idx_num=#{expectIdx}")
    int updateLastIdxWithCasMode(@Param("seqName") String seqName,@Param("newIdx") long newIdx,@Param("expectIdx") long expectIdx);

    @Insert("insert into common_seq (seq_name,last_idx_num) values (#{serialName},0)")
    void insertSeq(@Param("serialName") String serialName);
}
