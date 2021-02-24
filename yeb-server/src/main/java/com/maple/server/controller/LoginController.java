package com.maple.server.controller;

import com.maple.common.R;
import com.maple.server.pojo.Admin;
import com.maple.server.pojo.AdminLoginParam;
import com.maple.server.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Api(tags = "LoginController")
@RestController
public class LoginController {

    //注入service
    @Autowired
    private IAdminService adminService;


    @ApiOperation(value = "登录成功返回token")
    @PostMapping("/login")
    public R login(@RequestBody AdminLoginParam adminLoginParam, HttpServletRequest request){
        return adminService.login(adminLoginParam.getUsername(),adminLoginParam.getPassword(),request);
    }

    @ApiOperation(value = "获取当前用户信息")
    @GetMapping("/admin/info")
    public R getAdminInfo(Principal principal){
        if(principal == null){
            return R.error();
        }

        String username = principal.getName();
        Admin admin = adminService.getAdminByUserName(username);
        if(admin == null){
            return R.error();
        }
        return R.success().data("user",admin);
    }

    @ApiOperation(value = "用户退出")
    @PostMapping("/logout")
    public R logout(){
        return R.success().message("用户退出成功");
    }
}
