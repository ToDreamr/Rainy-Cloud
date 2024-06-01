package com.pray.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;

// 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
public class AppGenerator {



    //使用Mybatis的代码生成器
    public static void main(String[] args) {

        FastAutoGenerator.create(Constants.DB_URL, Constants.DB_USERNAME, Constants.DB_PASSWORD)
                .globalConfig(builder -> {
                    builder.author("春江花朝秋月夜") // 设置作者
//                            .fileOverride() // 覆盖已生成文件
                            .outputDir(Constants.PROJECT_ROOT + "\\cloud-common\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent(Constants.BASE_PACKAGE + ".app") ;// 设置父包名
                })
                .strategyConfig(builder -> {
                    builder // 设置需要生成的表名
                            .addInclude("sort").addInclude("im_chat_user_group_message","im_chat_user_message")
//                            .addInclude("article")
//                            .addInclude("comment")
//
//                            .addInclude("family","history_info","im_chat_group")
//                            .addInclude("label","im_chat_group_user","im_chat_user_friend")
//                            .addInclude("resource","resource_path","sys_config","tree_hole")
//                            .addInclude("user","web_info","weiyan")
                    ;

                    builder.entityBuilder()
                            .enableColumnConstant()
                            .enableLombok()
                            .addSuperEntityColumns(Constants.SUPER_ENTITY_COLUMNS)
                            .superClass(Constants.SUPER_ENTITY_CLASS);


                })
                .execute();
    }

}
