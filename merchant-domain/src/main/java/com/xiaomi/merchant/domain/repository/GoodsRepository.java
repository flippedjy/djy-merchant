package com.xiaomi.merchant.domain.repository;

import com.xiaomi.merchant.domain.entity.Goods;

public interface GoodsRepository {

    Goods find(String goodsId);
}
