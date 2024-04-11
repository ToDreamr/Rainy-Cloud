package com.pray.common;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.function.Consumer;

/**
 * <p>
 * BaseData
 * 实现继承自这个接口的实体的浅拷贝自动化工具
 * <p>
 *
 * @author 春江花朝秋月夜
 * @since 2023/8/26
 */
public interface BaseData {
    default <V> V asViewObject( Class<V> vClass ) throws Exception {
        Field[] fields = vClass.getDeclaredFields();//获取实体属性字段
        Constructor<V> constructor=vClass.getConstructor();//获取构造器
        V v = constructor.newInstance();//创建实例对象
        //对字段进行填充
        for (Field field : fields) {
            if (field!=null){
                convert(field,v);
            }
        }
        return v;
    }
    //函数式接口
    default <V> V asViewObject(Class<V> vClass, Consumer<V> consumer) throws Exception {
        V v=this.asViewObject(vClass);
        consumer.accept(v);//对属性进行自定义逻辑
        return v;
    }
    //填充字段
    private void convert(Field field,Object vo){
        try {
            Field source=this.getClass().getDeclaredField(field.getName());//从主体中获取字段
            //将字段权限设置为可取
            field.setAccessible(true);
            source.setAccessible(true);
            field.set(vo,source.get(this));
        } catch (NoSuchFieldException | IllegalAccessException ignored) {}
    }
}
