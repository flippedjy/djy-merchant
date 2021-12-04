package com.xiaomi.merchant.domain.constant;

public enum PayStatus {

    INIT("0","初始化"),
    IN_PROCESS("1","支付处理中"),
    SUCCESS("2","支付成功"),
    FAIL("-1","支付失败");


    private String code;

    private String desc;

    PayStatus(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }


}
