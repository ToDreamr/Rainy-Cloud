package com.pray.controller;

import com.pray.annotation.RequiresPermissions;
import com.pray.constants.UserConstants;
import com.pray.entity.Result;
import com.pray.entity.sys.SysMenu;
import com.pray.service.ISysMenuService;
import com.pray.service.ISysUserService;
import com.pray.utils.StringUtils;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.pray.entity.Result.success;
import static com.pray.entity.Result.toAjax;

/**
 * 菜单信息
 *
 * @author 春江花朝秋月夜
 */
@RestController
@RequestMapping("/system/menu")
public class SysMenuController extends BaseController {
    @Resource
    private ISysMenuService menuService;

    @Resource
    private ISysUserService userService;

    /**
     * 获取菜单列表
     */
    @RequiresPermissions("system:menu:list")
    @GetMapping("/list")
    public Result list(SysMenu menu) {
        //todo 分配用户id
//        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menus = menuService.selectMenuList(menu, 1l);
        return success(menus);
    }

    /**
     * 根据菜单编号获取详细信息
     */
    @RequiresPermissions("system:menu:query")
    @GetMapping(value = "/{menuId}")
    public Result getInfo(@PathVariable Long menuId) {
        return success(menuService.selectMenuById(menuId));
    }

    /**
     * 获取菜单下拉树列表
     */
    @GetMapping("/treeselect")
    public Result treeselect(SysMenu menu) {
        //todo 分配用户id
//        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menus = menuService.selectMenuList(menu, 1l);
        return success(menuService.buildMenuTreeSelect(menus));
    }

    /**
     * 加载对应角色菜单列表树
     */
    @GetMapping(value = "/roleMenuTreeselect/{roleId}")
    public Result roleMenuTreeselect(@PathVariable("roleId") Long roleId) {
        //todo 分配用户id
//        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menus = menuService.selectMenuList(1l);
        Result ajax = success();
        ajax.put("checkedKeys", menuService.selectMenuListByRoleId(roleId));
        ajax.put("menus", menuService.buildMenuTreeSelect(menus));
        return ajax;
    }

    /**
     * 新增菜单
     */
    @RequiresPermissions("system:menu:add")
    @PostMapping
    public Result add(@Validated @RequestBody SysMenu menu) {
        if (UserConstants.NOT_UNIQUE.equals(menuService.checkMenuNameUnique(menu))) {
            return Result.fail("新增菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        } else if (UserConstants.YES_FRAME.equals(menu.getIsFrame()) && !StringUtils.ishttp(menu.getPath())) {
            return Result.fail("新增菜单'" + menu.getMenuName() + "'失败，地址必须以http(s)://开头");
        }
        menu.setCreateBy("默认用户");
        return toAjax(menuService.insertMenu(menu));
    }

    /**
     * 修改菜单
     */
    @RequiresPermissions("system:menu:edit")
    @PutMapping
    public Result edit(@Validated @RequestBody SysMenu menu) {
        if (UserConstants.NOT_UNIQUE.equals(menuService.checkMenuNameUnique(menu))) {
            return Result.fail("修改菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        } else if (UserConstants.YES_FRAME.equals(menu.getIsFrame()) && !StringUtils.ishttp(menu.getPath())) {
            return Result.fail("修改菜单'" + menu.getMenuName() + "'失败，地址必须以http(s)://开头");
        } else if (menu.getMenuId().equals(menu.getParentId())) {
            return Result.fail("修改菜单'" + menu.getMenuName() + "'失败，上级菜单不能选择自己");
        }
        //todo 通过用户
        menu.setUpdateBy("默认用户");
        return toAjax(menuService.updateMenu(menu));
    }

    /**
     * 删除菜单
     */
    @RequiresPermissions("system:menu:remove")
    @DeleteMapping("/{menuId}")
    public Result remove(@PathVariable("menuId") Long menuId) {
//        if (menuService.hasChildByMenuId(menuId)) {
//            return warn("存在子菜单,不允许删除");
//        }
        if (menuService.checkMenuExistRole(menuId)) {
            return Result.warn("菜单已分配,不允许删除");
        }
        return toAjax(menuService.deleteMenuById(menuId));
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public Result getRouters() {
//        LoginUser loginUser = SecurityUtils.getLoginUser();
//        Long userId = loginUser.getUserId();
        //todo 通过用户id获取路由信息
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(1l);
        return success(menuService.buildMenus(menus));
    }
}