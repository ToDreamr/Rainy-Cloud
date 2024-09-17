package com.pray.annotation;

/**
 * 权限注解的验证模式
 * 
 * @author 春江花朝秋月夜
 *
 */
public enum Logical
{
    /**
     * 必须具有所有的元素
     */
    AND,

    /**
     * 只需具有其中一个元素
     */
    OR
}
