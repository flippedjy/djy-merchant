package com.xiaomi.merchant.domain.vo;

public class PayReqParam {

    private String deductId;

    private String bindId;

    private String cardNo;

    private String bankShortName;

    private String payType;

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getDeductId() {
        return deductId;
    }

    public void setDeductId(String deductId) {
        this.deductId = deductId;
    }

    public String getBindId() {
        return bindId;
    }

    public void setBindId(String bindId) {
        this.bindId = bindId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getBankShortName() {
        return bankShortName;
    }

    public void setBankShortName(String bankShortName) {
        this.bankShortName = bankShortName;
    }
}
