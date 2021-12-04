package com.xiaomi.merchant.domain.entity;

import com.xiaomi.merchant.type.Phone;

public class User {

    private String userId;

    private String name;

    private Phone phone;

    private String xiaomiId;

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public String getXiaomiId() {
        return xiaomiId;
    }

    public User(String userId, String name, Phone phone, String xiaomiId) {
        this.userId = userId;
        this.name = name;
        this.phone = phone;
        this.xiaomiId = xiaomiId;
    }
}
