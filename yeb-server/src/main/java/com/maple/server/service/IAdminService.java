package com.maple.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.maple.common.R;
import com.maple.server.pojo.Admin;
import com.maple.server.pojo.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gaoguanqi
 * @since 2021-02-23
 */
public interface IAdminService extends IService<Admin> {

    /**
     * 登录成功返回token
     * @param username
     * @param password
     * @param request
     * @return
     */
    R login(String username, String password, String code,HttpServletRequest request);

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    Admin getAdminByUserName(String username);

    /**
     * 根据用户id获取权限列表
     * @param adminId
     * @return
     */
    List<Role> getRolesByAdminId(Integer adminId);
}
