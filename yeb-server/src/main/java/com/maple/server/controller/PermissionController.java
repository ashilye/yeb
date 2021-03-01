package com.maple.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.maple.common.R;
import com.maple.server.pojo.Menu;
import com.maple.server.pojo.MenuRole;
import com.maple.server.pojo.Role;
import com.maple.server.service.IMenuRoleService;
import com.maple.server.service.IMenuService;
import com.maple.server.service.IRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限控制器
 */
@RestController
@RequestMapping("/system/basic/permission")
public class PermissionController {

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IMenuService menuService;
    @Autowired
    private IMenuRoleService menuRoleService;

    @ApiOperation(value = "获取所有角色列表")
    @GetMapping("/")
    public R getAllRoles(){
        List<Role> list = roleService.list();
        if(CollectionUtils.isEmpty(list)){
            return R.error().message("获取角色列表失败");
        }
        return R.success().data("list",list);
    }

    @ApiOperation(value = "添加角色")
    @PostMapping("/")
    public R addRole(@RequestBody Role role){
        if(!role.getName().startsWith("ROLE_")){
            role.setName("ROLE_" + role.getName());
        }
        if(isExist(role)){
            return R.error().message("添加失败,该角色已存在");
        }
        if(roleService.save(role)){
            return R.success().message("添加成功");
        }
        return R.error().message("添加失败");
    }

    @ApiOperation(value = "更新角色")
    @PutMapping("/")
    public R updateRole(@RequestBody Role role){
        if(isExist(role)){
            return R.error().message("更新失败,该角色已存在");
        }
        if(roleService.updateById(role)){
            return R.success().message("更新成功");
        }
        return R.error().message("更新失败");
    }

    @ApiOperation(value = "删除角色")
    @DeleteMapping("/deleteById/{id}")
    public R deleteRole(@PathVariable Integer id){
        if(roleService.removeById(id)){
            return R.success().message("删除成功");
        }
        return R.error().message("删除失败");
    }

    @ApiOperation(value = "批量删除角色")
    @DeleteMapping("/deleteByIds/{ids}")
    public R deleteRoles(Integer[] ids){
        if(roleService.removeByIds(Arrays.asList(ids))){
            return R.success().message("删除成功");
        }
        return R.error().message("删除失败");
    }


    /**
     * 判断是否存在
     * @param role
     * @return
     */
    private boolean isExist(Role role){
        List<Role> list = roleService.list();
        if(!CollectionUtils.isEmpty(list)){
            for (Role item : list) {
                if(role.getName().equalsIgnoreCase(item.getName()) && role.getNameZh().equalsIgnoreCase(item.getNameZh())){
                    return true;
                }
            }
        }
        return false;
    }

    @ApiOperation(value = "获取所有菜单")
    @GetMapping("/menus")
    private R getAllMenus(){
        List<Menu> list = menuService.getAllMenus();
        if(CollectionUtils.isEmpty(list)){
            return R.error().message("查询菜单失败");
        }
        return R.success().data("list",list);
    }

    @ApiOperation(value = "根据角色id查询菜单id")
    @GetMapping("/mid/{id}")
    private R getMenuIdByRoleId(@PathVariable Integer rid){
        List<Integer> idList = menuRoleService.list(new QueryWrapper<MenuRole>().eq("rid", rid)).stream().map(MenuRole::getId).collect(Collectors.toList());
        if(CollectionUtils.isEmpty(idList)){
            return R.error().message("查询失败");
        }
        return R.success().message("查询成功");
    }

    @ApiOperation(value = "更新角色菜单")
    @PutMapping("/updateMenuRole/{rid,mids}")
    private R updateMenuRole(Integer rid,Integer[] mids){
         return menuRoleService.updateMenuRole(rid,mids);
    }

}
