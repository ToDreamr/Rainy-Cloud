package com.pray.aop;

import cn.hutool.aop.aspects.Aspect;
import cn.hutool.aop.aspects.SimpleAspect;

import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * CloudLog
 *
 * @author 春江花朝秋月夜
 * @since 2024/3/21 18:07
 */
public class CloudLog extends SimpleAspect {
    Logger log= LoggerFactory.getLogger(CloudLog.class);
    /**
     * 代理的逻辑方法，所有动态代理类的方法调用都交给该方法处理
     * @param target 被代理对象
     * @param method 将要被执行的方法
     * @param args 执行方法需要的参数
     * @return 返回代理结果
     * @throws Throwable
     */
    @Override
    @Pointcut(value = "@annotation(com.pray.aop.PrayLog)")
    public boolean after(Object target, Method method, Object[] args, Object returnVal) {
        log.info("接收到方法：{}",method.getName());
        Aspect aspect=new SimpleAspect();
        return true;
    }
}
