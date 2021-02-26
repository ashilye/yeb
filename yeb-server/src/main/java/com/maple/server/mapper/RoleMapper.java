package com.maple.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.maple.server.pojo.Role;
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
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据用户id查询角色列表
     * @param adminId
     * @return
     */
    List<Role> getRolesByAdminId(Integer adminId);
}
