package com.projecty.component.iplimiit.utils;

import lombok.experimental.UtilityClass;

/**
 * @author 陈宇锋
 * @date 2024/1/5
 */
@UtilityClass
public class RedisKeyUtils {
    public static final String REDIS_KEY_PREFIX_TPLHK = "tplhk";
    public static final String SPLIT = ":";
    public static final String CLIENT_ID = "csp";
    public static final String BLACK_LIST = "blackList";
    public static final String TIMES = "times";

    public static final String REDIS_KEY_BLACK_LIST = REDIS_KEY_PREFIX_TPLHK + SPLIT + CLIENT_ID + SPLIT + BLACK_LIST;
    public static String generateRedisKey(String ipAddress, String requestURI){
        StringBuilder stringBuilder = new StringBuilder(REDIS_KEY_BLACK_LIST);
        StringBuilder keyBuilder = stringBuilder.append(SPLIT).append(ipAddress).append(SPLIT).append(requestURI);
        return keyBuilder.toString();
    }
}
