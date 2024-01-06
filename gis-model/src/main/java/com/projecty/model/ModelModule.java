package com.projecty.model;

import jakarta.persistence.EntityManager;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author 陈宇锋
 * @date 2024/1/4
 */
@Configuration
@ComponentScan(basePackages = {"com.projecty.model"})
@EnableJpaRepositories(basePackages = {"com.projecty.model.base.repository.policy"})
@EntityScan(basePackages = {"com.projecty.model.domain.*.entity"})
public class ModelModule {
    public static final String SYSTEM_USER = "system";
    public static final String SCHEMA_NB = "nb";
    public static final String SCHEMA_AUD = "aud";

    public interface TableConstants{
        String AUD_SUFFIX = "_aud";
        String TABLE_COMMENT = "t_comment";
        String TABLE_POLICY = "t_policy";
        String TABLE_POLICY_DATE = "t_policy_date_info";
        String TABLE_UW = "t_underwriting";
        String AUD_TABLE_COMMENT = TABLE_COMMENT + AUD_SUFFIX;
        String AUD_TABLE_POLICY = TABLE_POLICY + AUD_SUFFIX;
        String AUD_TABLE_POLICY_DATE = TABLE_POLICY_DATE + AUD_SUFFIX;
        String AUD_TABLE_UW = TABLE_UW + AUD_SUFFIX;
    }
}
