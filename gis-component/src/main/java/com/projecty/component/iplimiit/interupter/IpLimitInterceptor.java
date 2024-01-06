package com.projecty.component.iplimiit.interupter;

import com.projecty.component.iplimiit.annotation.SkipBlackListCheck;
import com.projecty.component.iplimiit.config.IpLimitProperty;
import com.projecty.component.iplimiit.utils.HttpRequestUtil;
import com.projecty.component.iplimiit.utils.RedisKeyUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static com.projecty.component.iplimiit.utils.RedisKeyUtils.REDIS_KEY_BLACK_LIST;
import static com.projecty.component.iplimiit.utils.RedisKeyUtils.TIMES;

/**
 * @author 陈宇锋
 * @date 2024/1/5
 */
@Order(value = Ordered.LOWEST_PRECEDENCE)
public class IpLimitInterceptor implements HandlerInterceptor{

    @Resource
    private IpLimitProperty ipLimitProperty;
    @Resource(name = "stringRedisTemplate")
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ipAddress = HttpRequestUtil.getClientIpAddress(request);
        String redisKey = RedisKeyUtils.generateRedisKey(ipAddress.replaceAll(":","_"), request.getRequestURI());
        Boolean inBlackList = redisTemplate.opsForSet().isMember(REDIS_KEY_BLACK_LIST, redisKey);
        if(inBlackList){
            return false;
        }
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            // 检查是否有SkipBlacklistCheck注解
            if (method.isAnnotationPresent(SkipBlackListCheck.class)) {
                //放行
                return true;
            }
        }
        //性能为先，不考虑并发问题
        Object times =  redisTemplate.opsForValue().get(redisKey);
        if(times == null){
            redisTemplate.opsForValue().set(redisKey,"1", Duration.ofMillis(ipLimitProperty.getIntervalMs()));
        }else{
            Long oldTtl = redisTemplate.getExpire(redisKey, TimeUnit.MILLISECONDS);
            Long currentTimes = redisTemplate.opsForValue().increment(redisKey);
            redisTemplate.expire(redisKey,oldTtl,TimeUnit.MILLISECONDS);
            if(currentTimes < ipLimitProperty.getMaxTimes()){
                //放行
                return true;
            }
            //加黑名单
            String ipKey = RedisKeyUtils.generateRedisKey(ipAddress.replaceAll(":","_"),TIMES);
            Long ipBanTimes = redisTemplate.opsForValue().increment(ipKey);
            int index = ipBanTimes.intValue()-1<ipLimitProperty.getBanMinutes().size()?ipBanTimes.intValue()-1:ipLimitProperty.getBanMinutes().size()-1;
            redisTemplate.opsForSet().add(REDIS_KEY_BLACK_LIST,ipAddress,Duration.ofMinutes(ipLimitProperty.getBanMinutes().get(index)));
            return false;
        }
        return true;
    }
}
