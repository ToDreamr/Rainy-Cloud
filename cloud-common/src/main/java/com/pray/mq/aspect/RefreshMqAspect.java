package com.pray.mq.aspect;

import com.pray.constants.RabbitMqConstants;
import com.pray.mq.annotation.RefreshMqSender;
import jakarta.annotation.Resource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * RefreshMqAspect
 *
 * @author 春江花朝秋月夜
 * @since 2024/6/1 16:02
 */
@Aspect
@Component
public class RefreshMqAspect {
    @Resource(name = "loacal-template")
    private RabbitTemplate rabbitTemplate;

    @Pointcut("@annotation(com.pray.mq.annotation.RefreshMqSender)")
    public void messageCut(){

    }
    @Around("messageCut()")
    public Object around(ProceedingJoinPoint point) throws  Throwable{
        Object result = point.proceed();
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        RefreshMqSender annotation = method.getAnnotation(RefreshMqSender.class);
        rabbitTemplate.convertAndSend("amq.direct", RabbitMqConstants.BOOK_BORROW_TOPIC,
                annotation.sender()+" "+annotation.message());
        return result;
    }
}
