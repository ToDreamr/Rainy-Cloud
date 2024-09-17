package com.pray.controller;

import cn.hutool.core.util.ObjectUtil;
import com.pray.annotation.RequiresPermissions;
import com.pray.auth.SecurityUtils;
import com.pray.constants.UserConstants;
import com.pray.domain.SysUserRole;
import com.pray.entity.Result;
import com.pray.entity.auth.AuthUser;
import com.pray.entity.sys.SysDept;
import com.pray.entity.sys.SysRole;
import com.pray.entity.sys.SysUser;
import com.pray.poi.ExcelUtil;
import com.pray.service.ISysDeptService;
import com.pray.service.ISysRoleService;
import com.pray.service.ISysUserService;
import com.pray.service.TokenService;
import com.pray.service.impl.SysPermissionService;
import com.pray.utils.StringUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.pray.entity.Result.success;
import static com.pray.entity.Result.toAjax;

/**
 * 角色信息
 *
 * @author 春江花朝秋月夜
 */
@RestController
@RequestMapping("/system/role")
public class SysRoleController extends BaseController {
    @Resource
    private ISysRoleService roleService;

    @Resource
    private TokenService tokenService;

    // TODO: 2024.04.24
//    @Resource
    private SysPermissionService permissionService;

    @Resource
    private ISysUserService userService;

    @Resource
    private ISysDeptService deptService;



    @RequiresPermissions("system:role:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysRole role) {
        List<SysRole> list = roleService.selectRoleList(role);
        ExcelUtil<SysRole> util = new ExcelUtil<SysRole>(SysRole.class);
        util.exportExcel(response, list, "角色数据");
    }

    /**
     * 根据角色编号获取详细信息
     */
    @RequiresPermissions("system:role:query")
    @GetMapping(value = "/{roleId}")
    public Result getInfo(@PathVariable Long roleId) {
        roleService.checkRoleDataScope(roleId);
        return success(roleService.selectRoleById(roleId));
    }

    /**
     * 新增角色
     */
    @RequiresPermissions("system:role:add")
    @PostMapping
    public Result add(@Validated @RequestBody SysRole role) {
        if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleNameUnique(role))) {
            return Result.fail("新增角色'" + role.getRoleName() + "'失败，角色名称已存在");
        } else if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleKeyUnique(role))) {
            return Result.fail("新增角色'" + role.getRoleName() + "'失败，角色权限已存在");
        }
        role.setCreateBy("默认用户");
        return toAjax(roleService.insertRole(role));

    }

    /**
     * 修改保存角色
     */
    @RequiresPermissions("@ss.hasPermi('system:role:edit')")
    @PutMapping
    public Result edit(@Validated @RequestBody SysRole role) {
        roleService.checkRoleAllowed(role);
        roleService.checkRoleDataScope(role.getRoleId());
        if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleNameUnique(role))) {
            return Result.fail("修改角色'" + role.getRoleName() + "'失败，角色名称已存在");
        } else if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleKeyUnique(role))) {
            return Result.fail("修改角色'" + role.getRoleName() + "'失败，角色权限已存在");
        }
        role.setUpdateBy("默认用户");

        if (roleService.updateRole(role) > 0) {
            // 更新缓存用户权限
            AuthUser loginUser = SecurityUtils.getLoginUser();
            if (StringUtils.isNotNull(loginUser.getUser()) && !loginUser.getUser().isAdmin()) {
                // TODO: 2024.04.24
//                loginUser.setPermissions(permissionService.getMenuPermission(loginUser.getUser()));
                loginUser.setUser(userService.selectUserByUserName(loginUser.getUser().getUserName()));
                tokenService.setLoginUser(loginUser);
            }
            // 角色相关的用户全部刷新权限
            SysUser user = new SysUser();
            user.setRoleId(role.getRoleId());
            List<SysUser> list = userService.selectAllocatedList(user);
            for (SysUser newUser:list){
                // TODO: 2024.04.24
//                tokenService.updateToken(new LoginUser(newUser.getUserId()
//                        , newUser.getDeptId()
//                        , newUser
//                        , permissionService.getMenuPermission(newUser)));
            }
            return success();
        }
        return Result.fail("修改角色'" + role.getRoleName() + "'失败，请联系管理员");
    }

    /**
     * 修改保存数据权限
     */
    @RequiresPermissions("system:role:edit")
    @PutMapping("/dataScope")
    public Result dataScope(@RequestBody SysRole role) {
        roleService.checkRoleAllowed(role);
        roleService.checkRoleDataScope(role.getRoleId());
        // 角色相关的用户
        SysUser user = new SysUser();
        user.setRoleId(role.getRoleId());
        List<SysUser> list = userService.selectAllocatedList(user);
        // 修改角色
        int count  = roleService.authDataScope(role);
        // 更新用户信息
        if (count>0){
            for (SysUser newUser:list){
                // 全部刷新权限
                // TODO: 2024.04.24  
//                tokenService.updateToken(new LoginUser(newUser.getUserId()
//                        , newUser.getDeptId()
//                        , newUser
//                        , permissionService.getMenuPermission(newUser)));
            }
        }
        return toAjax(count);
    }

    /**
     * 状态修改
     */
    @RequiresPermissions("system:role:edit")
    @PutMapping("/changeStatus")
    public Result changeStatus(@RequestBody SysRole role) {
        roleService.checkRoleAllowed(role);
        roleService.checkRoleDataScope(role.getRoleId());
        role.setUpdateBy("默认用户");
        // 角色相关的用户
        SysUser user = new SysUser();
        user.setRoleId(role.getRoleId());
        List<SysUser> list = userService.selectAllocatedList(user);
        // 修改角色
        int count  = roleService.updateRoleStatus(role);
        // 更新用户信息
        if (count>0){
            for (SysUser newUser:list){
                // TODO: 2024.04.24
//                // 全部刷新权限
//                tokenService.updateToken(new LoginUser(newUser.getUserId()
//                        , newUser.getDeptId()
//                        , newUser
//                        , permissionService.getMenuPermission(newUser)));
            }
        }
        return toAjax(count);
    }

    /**
     * 删除角色
     */
    @RequiresPermissions("system:role:remove")
    @DeleteMapping("/{roleIds}")
    public Result remove(@PathVariable Long[] roleIds) {
        List<SysUser> list = new ArrayList<>();
        for (Long roleId:roleIds){
            // 角色相关的用户
            SysUser user = new SysUser();
            user.setRoleId(roleId);
            list.addAll(userService.selectAllocatedList(user));
        }
        // 删除角色
        int count = roleService.deleteRoleByIds(roleIds);
        if (count>0){
            for (SysUser newUser:list){
                // 全部刷新权限
                // TODO: 2024.04.24  
//                tokenService.updateToken(new LoginUser(newUser.getUserId()
//                        , newUser.getDeptId()
//                        , newUser
//                        , permissionService.getMenuPermission(newUser)));
            }
        }
        return toAjax(count);
    }

    /**
     * 获取角色选择框列表
     */
    @RequiresPermissions("system:role:query")
    @GetMapping("/optionselect")
    public Result optionselect() {
        return success(roleService.selectRoleAll());
    }



    /**
     * 取消授权用户
     */
    @RequiresPermissions("system:role:edit")
    @PutMapping("/authUser/cancel")
    public Result cancelAuthUser(@RequestBody SysUserRole userRole) {
        int count = roleService.deleteAuthUser(userRole);
        // 如果完成用户修改，就刷新用户缓存
        if (count>0){
            SysUser newUser = userService.selectUserById(userRole.getUserId());
            if (ObjectUtil.isNotEmpty(newUser)){
                // TODO: 2024.04.24  
//                tokenService.updateToken(new LoginUser(newUser.getUserId()
//                        , newUser.getDeptId()
//                        , newUser
//                        , permissionService.getMenuPermission(newUser)));
            }
        }
        return toAjax(count);
    }

    /**
     * 批量取消授权用户
     */
    @RequiresPermissions("system:role:edit")
    @PutMapping("/authUser/cancelAll")
    public Result cancelAuthUserAll(Long roleId, Long[] userIds) {
        int count = roleService.deleteAuthUsers(roleId, userIds);
        // 如果完成用户修改，就刷新用户缓存
        if (count>0){
            for (Long userId:userIds){
                SysUser newUser = userService.selectUserById(userId);
                if (ObjectUtil.isNotEmpty(newUser)){
                    // TODO: 2024.04.24  
//                    tokenService.updateToken(new LoginUser(newUser.getUserId()
//                            , newUser.getDeptId()
//                            , newUser
//                            , permissionService.getMenuPermission(newUser)));
                }
            }
        }
        return toAjax(count);
    }

    /**
     * 批量选择用户授权
     */
    @RequiresPermissions("system:role:edit")
    @PutMapping("/authUser/selectAll")
    public Result selectAuthUserAll(Long roleId, Long[] userIds) {
        roleService.checkRoleDataScope(roleId);
        int count = roleService.insertAuthUsers(roleId, userIds);
        // 如果完成用户修改，就刷新用户缓存
        if (count>0){
            for (Long userId:userIds){
                SysUser newUser = userService.selectUserById(userId);
                if (ObjectUtil.isNotEmpty(newUser)){
                    // TODO: 2024.04.24
//                    tokenService.updateToken(new LoginUser(newUser.getUserId()
//                            , newUser.getDeptId()
//                            , newUser
//                            , permissionService.getMenuPermission(newUser)));
                }
            }
        }
        return toAjax(count);
    }

    /**
     * 获取对应角色部门树列表
     */
    @RequiresPermissions("system:role:query")
    @GetMapping(value = "/deptTree/{roleId}")
    public Result deptTree(@PathVariable("roleId") Long roleId) {
        Result ajax = success();
        ajax.put("checkedKeys", deptService.selectDeptListByRoleId(roleId));
        ajax.put("depts", deptService.selectDeptTreeList(new SysDept()));
        return ajax;
    }
}
