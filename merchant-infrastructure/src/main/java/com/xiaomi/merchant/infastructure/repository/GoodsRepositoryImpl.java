package com.xiaomi.merchant.infastructure.repository;

import com.xiaomi.merchant.domain.entity.Goods;
import com.xiaomi.merchant.domain.repository.GoodsRepository;
import org.springframework.stereotype.Repository;

@Repository
public class GoodsRepositoryImpl implements GoodsRepository {
    @Override
    public Goods find(String goodsId) {
        return null;
    }
}
