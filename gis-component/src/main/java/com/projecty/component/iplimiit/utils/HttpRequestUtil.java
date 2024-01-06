package com.projecty.component.iplimiit.utils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author 陈宇锋
 * @date 2023/11/30
 */
@UtilityClass
public final class HttpRequestUtil {

    private static final String UNKNOWN = "unknown";
    private static final String[] IP_HEADER_CANDIDATES = {"X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR", "HTTP_X_FORWARDED", "HTTP_X_CLUSTER_CLIENT_IP", "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR", "HTTP_FORWARDED", "HTTP_VIA", "REMOTE_ADDR", "Client-IP"};

    //获取HttpServletRequest
    public static HttpServletRequest getHttpServletRequest() {
        if (RequestContextHolder.getRequestAttributes() == null) {
            return null;
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        return request;
    }

    //获取客户端ip地址
    public static String getClientIpAddress() {
        HttpServletRequest request = getHttpServletRequest();
        if (request == null) {
            return "0.0.0.0";
        }
        for (String ipHeader : IP_HEADER_CANDIDATES) {
            String ipList = request.getHeader(ipHeader);
            if (ipList != null && ipList.length() != 0 && UNKNOWN.equalsIgnoreCase(ipList)) {
                String ip = ipList.split(",")[0];
                return ip;
            }
        }
        return request.getRemoteAddr();
    }

    public static String getClientIpAddress(HttpServletRequest request) {
        if (request == null) {
            return "0.0.0.0";
        }
        for (String ipHeader : IP_HEADER_CANDIDATES) {
            String ipList = request.getHeader(ipHeader);
            if (ipList != null && ipList.length() != 0 && UNKNOWN.equalsIgnoreCase(ipList)) {
                String ip = ipList.split(",")[0];
                return ip;
            }
        }
        return request.getRemoteAddr();
    }

    //获取客户端设备信息
    public static String getUserAgent() {
        HttpServletRequest request = getHttpServletRequest();
        if (request == null) {
            return "";
        }
        String userAgent = request.getHeader("User-Agent");
        return userAgent != null ? userAgent : request.getHeader("user-agent");
    }

    //获取客户端跳转来源 Referer
    public static String getClientReferer() {
        HttpServletRequest request = getHttpServletRequest();
        if (null == request) {
            return "";
        }
        String referer = request.getHeader("Referer");
        return referer != null ? referer : request.getHeader("referer");
    }

    //获取请求方式
    public static String gerRequestMethod() {
        HttpServletRequest request = getHttpServletRequest();
        if (null == request) {
            return "";
        }
        return request.getMethod();
    }

    //获取请求url
    public static String getRequestUrl() {
        HttpServletRequest request = getHttpServletRequest();
        if (request == null) {
            return "";
        }
        return request.getRequestURL().toString();
    }

    //获取请求资源URI
    public static String getRequestUri() {
        HttpServletRequest request = getHttpServletRequest();
        if (request == null) {
            return "";
        }
        return request.getRequestURI();
    }

    //获取QueryString
    public static String getRequestQueryString() {
        HttpServletRequest request = getHttpServletRequest();
        if (request == null) {
            return "";
        }
        return request.getQueryString();
    }

    //获取请求参数 Parameter by parameterName
    public static String getRequestParameter(String parameterName) {
        HttpServletRequest request = getHttpServletRequest();
        if (null == request) {
            return null;
        }
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (null != parameterMap) {
            String[] parameters = parameterMap.get(parameterName);
            return Optional.ofNullable(parameters)
                    .stream()
                    .map(str -> Arrays.stream(str)
                            .collect(Collectors.joining(",")))
                    .findFirst().orElse(null);
        }
        return null;
    }

    //获取请求头 header by name
    public static String getRequestHeader(String name) {
        HttpServletRequest request = getHttpServletRequest();
        if (null == request) {
            return null;
        }
        return request.getHeader(name);
    }

    //获取请求Attribute by name
    public static Object getRequestAttribute(String attrName) {
        HttpServletRequest request = getHttpServletRequest();
        if (null == request) {
            return null;
        }
        return request.getAttribute(attrName);
    }


}
