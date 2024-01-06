package com.projecty.model.base.config;

import com.projecty.model.ModelModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * @author 陈宇锋
 * @date 2024/1/4
 */
@Configuration
public class AuditConfig {
    @Bean
    public AuditorAware<String> auditorProvider() {
        return new DefaultAuditorAwareImpl();
    }

    public static class DefaultAuditorAwareImpl implements AuditorAware<String> {

        @Override
        public Optional<String> getCurrentAuditor() {
            SecurityContext securityContext = SecurityContextHolder.getContext();
            if(securityContext == null){
                return Optional.of(ModelModule.SYSTEM_USER);
            }
            Optional<String> userName = Optional.ofNullable(securityContext.getAuthentication().getName());
            return Optional.of(userName.orElse(ModelModule.SYSTEM_USER));
        }
    }
}
