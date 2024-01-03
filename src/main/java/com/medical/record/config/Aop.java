package com.medical.record.config;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author urey.liu
 * @description
 * @date 2023/5/24 8:29 下午
 */
@Slf4j
@Aspect
public class Aop {

    @Pointcut("execution(* com.medical.record.controller(..))")
    public void myMethodPointcut() {}

    @Around("myMethodPointcut()")
    public Object beforeMyMethod(ProceedingJoinPoint point) {
        // 在方法执行前执行的逻辑
        //定义返回值
        Object result = null;
        try {
            //获得切入点中方法的参数列表
            Object[] args = point.getArgs();
            log.info("params is {}", JSONObject.toJSONString(args));
            //执行切入点的方法
            result = point.proceed(args);
        } catch (Exception e){
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }
}
