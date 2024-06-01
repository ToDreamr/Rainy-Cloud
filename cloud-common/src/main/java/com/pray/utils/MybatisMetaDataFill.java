package com.pray.utils;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * MybatisMetaDataFill
 *
 * @author 春江花朝秋月夜
 * @since 2024/6/1 16:13
 */
@Component
public class MybatisMetaDataFill implements MetaObjectHandler {
        /**
         * 插入时填充
         *
         * @param metaObject
         */
        @Override
        public void insertFill(MetaObject metaObject) {
            // 填充创建时间 更新时间字段
            Date now = new Date();
            this.setFieldValByName("createTime", now, metaObject);
            this.setFieldValByName("updateTime", now, metaObject);
        }

        /**
         * 更新时填充
         *
         * @param metaObject
         */
        @Override
        public void updateFill(MetaObject metaObject) {
            // 填充给更新时间字段
            this.setFieldValByName("updateTime", new Date(), metaObject);
        }
}
