package com.xiaomi.merchant.infastructure.dataobj;

import com.xiaomi.merchant.type.Phone;

public class UserDo {


    private String userId;

    private String name;

    private String phone;

    private String xiaomiId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getXiaomiId() {
        return xiaomiId;
    }

    public void setXiaomiId(String xiaomiId) {
        this.xiaomiId = xiaomiId;
    }
}
