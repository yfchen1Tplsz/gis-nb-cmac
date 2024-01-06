package com.projecty.infrastucture.constants;

import java.net.URI;

/**
 * @author 陈宇锋
 * @date 2024/1/4
 */
public interface ErrorConstants {

    public static final String DOMAIN_NAME = "projecty.com";
    public static final String PROBLEM_BASE_URL = "https://"+DOMAIN_NAME+"/problem";
    public static final URI APPLICATION_EXCEPTION_TYPE = URI.create(PROBLEM_BASE_URL + "/application-exception");
}
