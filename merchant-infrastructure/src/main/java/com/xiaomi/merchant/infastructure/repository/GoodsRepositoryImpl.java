package com.xiaomi.merchant.infastructure.repository;

import com.xiaomi.merchant.domain.entity.Goods;
import com.xiaomi.merchant.domain.repository.GoodsRepository;
import com.xiaomi.merchant.infastructure.convert.GoodsConverter;
import com.xiaomi.merchant.infastructure.dao.GoodsDao;
import com.xiaomi.merchant.infastructure.dataobj.GoodsDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class GoodsRepositoryImpl implements GoodsRepository {

    @Autowired
    private GoodsDao goodsDao;

    @Override
    public Goods find(String goodsId) {
        GoodsDo goodsDo = goodsDao.selectById(goodsId);
        return GoodsConverter.convert(goodsDo);

    }

    @Override
    public List<Goods> findAll(List<String> goodsIds) {
        List<GoodsDo> goodsDos = goodsDao.selectInIds(goodsIds);
        return goodsDos.stream().map(GoodsConverter::convert).collect(Collectors.toList());
    }
}
