package com.projecty.infrastucture.enums;

/**
 * @author 陈宇锋
 * @date 2024/1/4
 */
public enum UwResultType {
    STD("1", "标体通过"),

    PST("2", "搁置"),

    WTH("3", "撤件"),

    DCL("4", "拒保"),

    SUB("5", "条件通过"),

    WFD("6", "待決定");

    private String code;
    private String desc;

    UwResultType(String code, String desc) {
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
        for (UwResultType value : values()) {
            if (value.code().equals(code)) {
                return true;
            }
        }
        return false;
    }
}
