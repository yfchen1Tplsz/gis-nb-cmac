package com.projecty.infrastucture.enums;
/**
 * @author 陈宇锋
 * @date 2024/1/4
 */
public enum PolicyStatusType {
    NB("1", "NB"),

    UW("2", "UW"),

    PRP("3", "PRP"),

    STD("4", "STD"),

    SUB("5", "SUB"),

    INFORCE("6", "INFORCE"),

    WTH("7", "WTH"),

    DCL("8", "DCL"),

    PST("9", "PST"),
    TERMINATED("10", "TERMINATED"),
    LAPSED("11", "LAPSED");

    private String code;
    private String desc;

    PolicyStatusType(String code, String desc) {
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
        for (PolicyStatusType value : values()) {
            if (value.code().equals(code)) {
                return true;
            }
        }
        return false;
    }
}
