package com.pray.generator;

public interface Constants {

//    String PROJECT_ROOT = "C:\\Users\\yjwfn\\Desktop\\project\\edatope";
    String PROJECT_ROOT = "D:\\springcloud-nacos-feign-seata";

    String DB_URL = "jdbc:mysql://localhost:3306/bootdemo?serverTimezone=Asia/Shanghai&characterEncoding=UTF-8&useSSL=false";

    String DB_USERNAME = "root";

    String DB_PASSWORD = "123456";

    String BASE_PACKAGE = "com.pray";

    String SUPER_ENTITY_CLASS = "com.pray.entity.BaseEntity";

    String[] SUPER_ENTITY_COLUMNS = new String[]{
            "id", "update_by", "create_by", "update_time", "create_time", "deleted"
    } ;


}
