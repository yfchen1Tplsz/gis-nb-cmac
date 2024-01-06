package com.projecty.infrastucture.utils;

import com.projecty.infrastucture.exception.BusinessException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * @author 陈宇锋
 * @date 2024/1/4
 */
public class SecurityUtils {
    public static String getAuthenticationUser(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if(securityContext == null){
            throw new BusinessException("沒有用戶登錄信息", "");
        }
        return Optional.ofNullable(securityContext.getAuthentication().getName())
                .orElseThrow(() -> new BusinessException("沒有用戶登錄信息", ""));
    }
}
