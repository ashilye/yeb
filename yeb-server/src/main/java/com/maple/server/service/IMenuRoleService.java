package com.maple.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.maple.common.R;
import com.maple.server.pojo.MenuRole;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gaoguanqi
 * @since 2021-02-23
 */
public interface IMenuRoleService extends IService<MenuRole> {

    /**
     * 更新角色菜单
     * @param rid
     * @param mids
     * @return
     */
    R updateMenuRole(Integer rid, Integer[] mids);
}
