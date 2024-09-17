package com.pray.bean;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

import javax.xml.validation.Validator;
import java.util.HashSet;
import java.util.Set;

/**
 * bean对象属性验证
 *
 * @author 春江花朝秋月夜
 */
public class BeanValidators {
    public static void validateWithException(Validator validator, Object object, Class<?>... groups)
            throws ConstraintViolationException {
        //todo 修改验证逻辑
        Set<ConstraintViolation<Object>> constraintViolations = new HashSet<>();
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }
}
