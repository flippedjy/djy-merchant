package com.xiaomi.merchant.domain.repository;

import com.xiaomi.merchant.domain.entity.Goods;

import java.util.List;

public interface GoodsRepository {

    Goods find(String goodsId);

    List<Goods> findAll(List<String> goodsIds);
}
