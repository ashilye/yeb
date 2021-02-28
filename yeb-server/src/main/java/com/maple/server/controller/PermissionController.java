package com.maple.server.controller;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.maple.common.R;
import com.maple.server.pojo.Role;
import com.maple.server.service.IRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * 权限控制器
 */
@RestController
@RequestMapping("/system/basic/permission")
public class PermissionController {

    @Autowired
    private IRoleService roleService;

    @ApiOperation(value = "获取所有权限列表")
    @GetMapping("/")
    public R getAllRoles(){
        List<Role> list = roleService.list();
        if(CollectionUtils.isEmpty(list)){
            return R.error().message("获取权限列表失败");
        }
        return R.success().data("list",list);
    }

    @ApiOperation(value = "添加权限")
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

}
