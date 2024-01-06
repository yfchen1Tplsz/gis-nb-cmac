package com.projecty.component.bootstrap.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author 陈宇锋
 * @date 2024/1/4
 */
@Configuration
public class AppConfig {
    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager entityManager){
        return  new JPAQueryFactory(entityManager);
    }
/*
    @Bean
    public EntityManager entityManager(EntityManagerFactory entityManagerFactory){
        return entityManagerFactory.createEntityManager();
    }*/

}
