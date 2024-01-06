package com.projecty.infrastucture.enums;

/**
 * @author 陈宇锋
 * @date 2024/1/4
 */
public enum YesOrNoType {
    YES("Y", "是"),
    NO("N", "否");

    private String code;
    private String desc;

    YesOrNoType(String code, String desc) {
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
        for (YesOrNoType value : values()) {
            if (value.code().equals(code)) {
                return true;
            }
        }
        return false;
    }
}
