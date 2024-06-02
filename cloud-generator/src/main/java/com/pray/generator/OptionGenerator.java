package com.pray.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;

// 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
public class OptionGenerator {



    public static void main(String[] args) {

        FastAutoGenerator.create(Constants.DB_URL, Constants.DB_USERNAME, Constants.DB_PASSWORD)
                .globalConfig(builder -> {
                    builder.author("春江花朝秋月夜") // 设置作者
//                            .fileOverride() // 覆盖已生成文件
                            .outputDir(Constants.PROJECT_ROOT + "\\cloud-option\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent(Constants.BASE_PACKAGE + ".option") ;// 设置父包名
                })
                .strategyConfig(builder -> {
                    builder // 设置需要生成的表名
                            .addInclude("sys_option")
                            .addTablePrefix("sys_acl", "sys"); // 设置过滤表前缀
                    builder.entityBuilder()
                            .enableColumnConstant()
                            .enableLombok()
                            .disableSerialVersionUID()
                            .addSuperEntityColumns(Constants.SUPER_ENTITY_COLUMNS)
                            .superClass(Constants.SUPER_ENTITY_CLASS);


                })
                .execute();
    }

}
