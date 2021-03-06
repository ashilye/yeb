package com.maple.server.controller;

import com.maple.common.R;
import com.maple.server.pojo.Admin;
import com.maple.server.pojo.AdminLoginParam;
import com.maple.server.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Api(tags = "AdminLoginController")
@RestController
public class AdminLoginController {
    protected Log log = LogFactory.getLog(this.getClass());

    //注入service
    @Autowired
    private IAdminService adminService;


    @ApiOperation(value = "登录成功返回token")
    @PostMapping("/login")
    public R login(@RequestBody AdminLoginParam adminLoginParam, HttpServletRequest request){
        return adminService.login(adminLoginParam.getUsername(),adminLoginParam.getPassword(),adminLoginParam.getCode(),request);
    }

    @ApiOperation(value = "获取当前用户信息")
    @GetMapping("/admin/info")
    public R getAdminInfo(Principal principal){
        if(principal == null){
            return R.error().message("未查询到该用户!");
        }

        String username = principal.getName();
        Admin admin = adminService.getAdminByUserName(username);
        if(admin == null){
            return R.error().message("未查询到该用户!!!");
        }
        admin.setPassword(null);
        // 添加权限
        admin.setRoles(adminService.getRolesByAdminId(admin.getId()));
        System.out.println("admin->>:" + admin.getUsername());
        return R.success().data("user",admin);
    }

    @ApiOperation(value = "用户退出")
    @PostMapping("/logout")
    public R logout(){
        return R.success().message("用户退出成功");
    }
}
