package com.maple.server.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.maple.server.pojo.Admin;
import com.maple.server.pojo.Menu;
import com.maple.server.mapper.MenuMapper;
import com.maple.server.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maple.server.utils.AdminUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gaoguanqi
 * @since 2021-02-23
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 通过用户id查询菜单列表
     * @return
     */
    @Override
    public List<Menu> getMenusByAdminId() {
        //数据库查询
        return menuMapper.getMenusByAdminId(AdminUtils.getCurrentAdmin().getId());

        //redis 查询
//        Integer adminId = AdminUtils.getCurrentAdmin().getId();
//        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
//        //从redis 拿菜单数据
//        List<Menu> menus = (List<Menu>) valueOperations.get("menu_" + adminId);
//        //如果 为空从数据库获取数据
//        if (CollectionUtils.isEmpty(menus)) {
//            menus = menuMapper.getMenusByAdminId(adminId);
//            // 把从数据库获取到的数据放到redis中
//            valueOperations.set("menu_" + adminId, menus);
//        }
//        return menus;
    }

    /**
     * 根据角色获取菜单列表
     * @return
     */
    @Override
    public List<Menu> getMenusWithRole() {

        return menuMapper.getMenusWithRole();
    }

}
