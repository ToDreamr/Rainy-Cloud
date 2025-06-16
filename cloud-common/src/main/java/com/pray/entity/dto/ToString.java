package com.pray.entity.dto;


import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * ToString
 * <p>
 *
 * @author 花行 (Rain)
 * @since 2025/5/26 9:56
 */
public abstract  class ToString implements Serializable {

    /**
     * 构造方法
     */
    public ToString() {
    }

    /**
     * 重写toString方法
     *
     * @return
     */
    @Override
    public String toString() {
        List<String> fieldStrings = new ArrayList<>();
        for (Field field : getClass().getDeclaredFields()) {
            field.setAccessible(true); // 允许访问私有字段
            try {
                Object value = field.get(this);
                fieldStrings.add(field.getName() + "=" + toStringSafe(value));
            } catch (IllegalAccessException e) {
                fieldStrings.add(field.getName() + "=ERROR");
            }
        }
        return getClass().getSimpleName() + "{" + String.join(", ", fieldStrings) + "}";
    }

    protected String toStringSafe(Object obj) {
        if (obj == null) {
            return "null";
        } else if (obj instanceof CharSequence) {
            return "\"" + obj + "\""; // 字符串加引号
        } else {
            return obj.toString();
        }
    }
}
