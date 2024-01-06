package com.projecty.component.bootstrap;

import com.projecty.application.ApplicationModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author 陈宇锋
 * @date 2024/1/4
 */
@SpringBootApplication(scanBasePackages = "com.projecty.component")
@EnableJpaRepositories({"com.projecty.component.*.repository"})
@EntityScan({"com.projecty.component.*.entity"})
@Import({ApplicationModule.class})
public class ComponentModule {
    public static void main(String[] args) {
        SpringApplication.run(ComponentModule.class, args);
    }


}
