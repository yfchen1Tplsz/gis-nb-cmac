package com.projecty.infrastucture.enums;
/**
 * @author 陈宇锋
 * @date 2024/1/4
 */
public enum PolicyCurrencyType {
    HKD("HKD", "港幣"),
    MOP("MOP", "澳門元"),
    CNY("CNY", "人民幣"),
    USD("USD", "美元");

    private String code;
    private String desc;

    PolicyCurrencyType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    public String code() {
        return code;
    }

    public String desc() {
        return desc;
    }

    public static boolean containsCode(String code) {
        for (PolicyCurrencyType value : values()) {
            if (value.code().equals(code)) {
                return true;
            }
        }
        return false;
    }
}
