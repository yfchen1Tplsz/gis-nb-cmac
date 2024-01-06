package com.projecty.component.repeat;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author 陈宇锋
 * @date 2023/11/29
 */
@Slf4j
@ConditionalOnBean({identityKeyProvider.class})
@ConditionalOnClass(StringRedisTemplate.class)
@Aspect
@Component
@RequiredArgsConstructor
public class RepeatSubmitAspect {
    private final StringRedisTemplate redisTemplate;
    private static final String REPEAT_REDIS_PREFIX = "repeat:";

    private final identityKeyProvider provider;

    @Around(value = "@annotation(repeatSubmit)")
    public Object around(ProceedingJoinPoint joinPoint, RepeatSubmit repeatSubmit) throws Throwable {
        //1 get user sessionId
        Optional<String> identityKeyOp = provider.extractIdentityKey();
        if (!identityKeyOp.isPresent()) {
            return joinPoint.proceed();
        }
        String identityKey = identityKeyOp.get();
        //2 concat redis key
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String className = method.getDeclaringClass().getName();
        String methodName = method.getName();
        String redisKey = identityKey + "#" + className + "#" + methodName;
        String key = REPEAT_REDIS_PREFIX + String.valueOf(redisKey.hashCode());
        log.info("key: {}, prefix:Hash: = {}", redisKey, key);
        int interval = repeatSubmit.Interval();
        log.info("set interval:{} ms", interval);


        //3 setnx key
        Boolean doSuccess = redisTemplate.opsForValue().setIfAbsent(key, "1", interval, TimeUnit.MILLISECONDS); //setnx
        if (doSuccess) {
            //成功插入，说明是首次提交
            return joinPoint.proceed();
        } else {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            //插入失败，说明是重复提交
            return ResponseEntity.badRequest().body(new HashMap<String, String>() {{
                put("status", "Bad Request");
                put("title", "Duplicated submission");
                put("path", request.getRequestURI());
            }});
        }
    }

}
