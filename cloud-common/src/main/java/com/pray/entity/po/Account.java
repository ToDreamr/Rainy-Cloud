package com.pray.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * 用户表
 * @TableName user
 */
@TableName(value ="user")//添加数据库表名前缀
@Data
public class Account implements Serializable {
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户唯一id,设置为自增主键，看来是设置了一个之后自动进行表id的 检验了
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String email;

    /**
     * 用户是否启用？1：启用，0：禁用当前用户
     */
    private boolean enabled;

    //通过对下面两个字段的检测实现对权限的控制
    @TableField(exist = false)
    private List<AuthRole> authRoles;
    @TableField(exist = false)
    private List<AuthPerm> authPerms;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    //权限认证，不会存在于表里面，主要是去写AuthorizationFilter

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(username, account.username) && Objects.equals(password, account.password) && Objects.equals(id, account.id) && Objects.equals(email, account.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, id, email);
    }


}
