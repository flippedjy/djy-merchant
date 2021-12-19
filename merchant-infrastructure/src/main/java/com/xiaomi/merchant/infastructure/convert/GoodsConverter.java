package com.xiaomi.merchant.infastructure.convert;

import com.xiaomi.merchant.domain.vo.Goods;
import com.xiaomi.merchant.infastructure.dataobj.GoodsDo;

public class GoodsConverter {

    public static Goods convert(GoodsDo goodsDo){
        if(goodsDo == null){
            throw new NullPointerException("GoodsDo must not be null");
        }

        return new Goods(goodsDo.getId(),goodsDo.getGoodsName(),goodsDo.getGoodsDesc(),goodsDo.getPrice());
    }
}
