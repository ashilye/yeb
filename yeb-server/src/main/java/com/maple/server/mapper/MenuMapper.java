package com.maple.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.maple.server.pojo.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gaoguanqi
 * @since 2021-02-23
 */
@Repository
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 通过用户id查询菜单列表
     * @param id
     * @return
     */
    List<Menu> getMenusByAdminId(Integer id);

    /**
     * 根据角色获取菜单列表
     * @return
     */
    List<Menu> getMenusWithRole();
}
