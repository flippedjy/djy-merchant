package com.xiaomi.merchant.type;

public class PriceFen {

    private long price;

    public PriceFen(long price) {
        if(price < 0){
            throw new IllegalArgumentException("price must be postive number");
        }
        this.price = price;
    }

    public long getPrice() {
        return price;
    }
}
