package com.projecty.component.iplimiit.config;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author 陈宇锋
 * @date 2024/1/5
 */
@Configuration
@Slf4j
@Getter
public class IpLimitProperty implements InitializingBean {
    @Value(value = "${black-list.interval-ms:10000}0")
    private Long intervalMs;

    @Value(value = "${black-list.max-times:20}")
    private Integer maxTimes;

    @Value(value = "#{'${black-list.ban-level:60,180,720,1440}'.split(',')}")
    private List<Integer> banMinutes;

    @Override
    public void afterPropertiesSet() throws Exception {
        //校验入参 ...
        log.info("intervalMs: {}ms",intervalMs);
        log.info("maxTimes: {}",maxTimes);
        log.info("banMinutes: {} minutes", banMinutes.toString());
        log.info("\n黑名单封禁规则：{}ms 内 同一个ip访问同一个接口达到 {} 次，则对ip进行封禁！",intervalMs,maxTimes);
        log.info("\n封禁时间按曾经被封禁的次数来确定等级，每次都增加时间，分别为: {} minutes ",banMinutes.toString());
    }


}
