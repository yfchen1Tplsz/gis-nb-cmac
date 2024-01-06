package com.projecty.component.iplimiit.config;

import com.projecty.component.iplimiit.interupter.IpLimitInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 陈宇锋
 * @date 2024/1/5
 */

@Configuration
public class IpLimitConfig implements WebMvcConfigurer {
    @Bean
    public IpLimitInterceptor ipLimitInterceptor(){
        return new IpLimitInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(ipLimitInterceptor()).excludePathPatterns("/static/**").addPathPatterns("/**");
    }
}
