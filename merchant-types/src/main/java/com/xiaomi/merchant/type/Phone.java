package com.xiaomi.merchant.type;

import org.apache.commons.lang3.StringUtils;

public class Phone {
    private String phoneNumber;

    public static final String REGEX_MOBILE = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";

    public Phone(String phoneNumber) {
        if(StringUtils.isBlank(phoneNumber) || !phoneNumber.matches(phoneNumber)){
            throw new IllegalArgumentException("illegal phone num");
        }
        this.phoneNumber = phoneNumber;
    }
}
