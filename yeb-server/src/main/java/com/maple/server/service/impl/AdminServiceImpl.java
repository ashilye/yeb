package com.maple.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.maple.common.R;
import com.maple.server.config.jwt.JwtTokenUtil;
import com.maple.server.mapper.RoleMapper;
import com.maple.server.pojo.Admin;
import com.maple.server.mapper.AdminMapper;
import com.maple.server.pojo.Role;
import com.maple.server.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gaoguanqi
 * @since 2021-02-23
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.tokenHead}") //application.yml 中 jwt头部
    private String tokenHead;

    // mapper 查询数据库
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private RoleMapper roleMapper;



    /**
     * 登录成功返回token
     * @param username
     * @param password
     * @param request
     * @return
     */
    @Override
    public R login(String username, String password, String code,HttpServletRequest request) {
        //先判断验证码
        String captcha = (String) request.getSession().getAttribute("captcha");
        if(!StringUtils.hasLength(captcha) || !captcha.equalsIgnoreCase(code)){
            return R.error().message("验证码填写错误,请重新填写！");
        }
        // 用户名密码校验
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        System.out.println("username->>:" + username);
        System.out.println("password->>:" + password);
        if(userDetails == null || !passwordEncoder.matches(password,userDetails.getPassword())){
            return R.error().message("用户名或密码不正确");
        }

        if(!userDetails.isEnabled()){
            return R.error().message("该账号已禁用,请联系管理员");
        }
        // 生成token
        String token = jwtTokenUtil.generateToken(userDetails);
        if(!StringUtils.hasLength(token)){
            return R.error().message("token 错误");
        }
        // 更新security 登录用户对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);


        Map<String,Object> tokenMap = new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("tokenHead",tokenHead);
        return R.success().message("登录成功").data(tokenMap);
    }

    @Override
    public Admin getAdminByUserName(String username) {
        Admin admin = adminMapper.selectOne(new QueryWrapper<Admin>().eq("username",username).eq("enabled",true));
        return admin;
    }

    /**
     * 根据用户id获取权限列表
     * @param adminId
     * @return
     */
    @Override
    public List<Role> getRolesByAdminId(Integer adminId) {
        return roleMapper.getRolesByAdminId(adminId);
    }
}
