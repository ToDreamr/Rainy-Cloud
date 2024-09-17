package com.pray.entity.vo;


import com.pray.annotation.Excel;
import com.pray.entity.sys.SysUser;

/**
 * 用户对象 sys_user
 *
 * @author 春江花朝秋月夜
 */
public class SysUserVO extends SysUser {

    /**
     * 是否绑定企微
     */
    @Excel(name = "企微状态")
    private String userWxNameState;

    public String getUserWxNameState() {
        return userWxNameState;
    }

    public void setUserWxNameState(String userWxNameState) {
        this.userWxNameState = userWxNameState;
    }
}
