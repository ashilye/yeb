package com.maple.server.controller;


import com.maple.common.R;
import com.maple.server.pojo.Menu;
import com.maple.server.service.IAdminService;
import com.maple.server.service.IMenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gaoguanqi
 * @since 2021-02-23
 */
@RestController
@RequestMapping("/system/cfg")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    /**
     * 登录成功后的用户,服务端可通过全局SecurityContextHolder获取到当前用户id
     * @return
     */
    @ApiOperation(value = "通过用户id查询菜单列表")
    @GetMapping("/menu")
    public R getMenusByAdminId(){
        List<Menu> menuList = menuService.getMenusByAdminId();
        if(menuList == null && menuList.isEmpty()){
            return R.error().message("查询菜单失败");
        }
        return R.success().data("menus",menuList);
    }
}
