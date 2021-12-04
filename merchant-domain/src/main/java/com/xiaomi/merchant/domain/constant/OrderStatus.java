package com.xiaomi.merchant.domain.constant;

public enum OrderStatus {

    IN_PROCESS("1","支付处理中"),
    SUCCESS("2","支付成功"),
    NOT_PAY("0","未支付"),
    CLOSE("-1","订单关闭");

    private String code;

    private String desc;

    OrderStatus(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
