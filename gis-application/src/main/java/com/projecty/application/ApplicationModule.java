package com.projecty.application;

import com.projecty.model.ModelModule;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * @author 陈宇锋
 * @date 2024/1/4
 */
@Configuration
@ComponentScan(basePackages = "com.projecty.application.service")
@Import({ModelModule.class})
public class ApplicationModule {
}
