package cn.njyazheng.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Objects;

@Component
@Aspect
public class TimeAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(TimeAspect.class);
    //第一个*代表任何返回方法
    @Around("execution(* cn.njyazheng.*.*Controller.*(..))")
    public Object handlerControllerMethod(ProceedingJoinPoint point) throws Throwable {
        LOGGER.info("-----------------------TimeAspect start");
        LocalTime start=LocalTime.now();
        Object[]objects=point.getArgs();
        Arrays.stream(objects).forEach(o -> LOGGER.info("-----------------------TimeAspect method agrs:"+o));
        Object object= point.proceed();
        LocalTime end=LocalTime.now();
        LOGGER.info("-----------------------TimeAspect end");
        LOGGER.info("-----------------------TimeAspect execute time:"+ Duration.between(start,end).toMillis()+"ms");
        return object;
    }
    
    
}
