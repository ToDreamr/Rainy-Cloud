package com.pray.filter;

import com.pray.exception.CloudException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * SqlFilter
 *
 * @author 春江花朝秋月夜
 * @since 2024/6/1 15:30
 */
@Component
public class SqlFilter {

        /**
         * SQL注入过滤
         * @param str  待验证的字符串
         */
        public static String sqlInject(String str){
            if(StringUtils.isEmpty(str)){
                return null;
            }
            //去掉'|"|;|\字符
            str = StringUtils.replace(str, "'", "");
            str = StringUtils.replace(str, "\"", "");
            str = StringUtils.replace(str, ";", "");
            str = StringUtils.replace(str, "\\", "");

            //转换成小写
            str = str.toLowerCase();

            //非法字符
            String[] keywords = {"master", "truncate", "insert", "select", "delete", "update", "declare", "alert", "drop"};

            //判断是否包含非法字符
            for(String keyword : keywords){
                if(str.indexOf(keyword) != -1){
                    throw new CloudException("SQL非法");
                }
            }
            return str;
        }
}
