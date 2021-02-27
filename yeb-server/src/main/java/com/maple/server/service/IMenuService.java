package com.maple.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.maple.server.pojo.Menu;
import com.maple.server.pojo.Role;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gaoguanqi
 * @since 2021-02-23
 */
public interface IMenuService extends IService<Menu> {

    /**
     * 通过用户id查询菜单列表
     * @return
     */
    List<Menu> getMenusByAdminId();

    /**
     * 根据角色获取菜单列表
     * @return
     */
    List<Menu> getMenusWithRole();
}
