package com.maple.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.maple.server.pojo.MenuRole;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gaoguanqi
 * @since 2021-02-23
 */
@Repository
public interface MenuRoleMapper extends BaseMapper<MenuRole> {


    Integer insertRecord(@Param("rid") Integer rid, @Param("mids") Integer[] mids);
}
