package com.pray.mq.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RefreshMqSender {
    String sender();

    String message() default "sending a refresh msg to MQ";
}
