package com.g5619.controller;

import com.g5619.config.Telnet;
import com.g5619.entity.User;
import com.g5619.entity.res.UpdateUserReq;
import com.g5619.service.UserService;
import com.g5619.shiro.JwtToken;
import com.g5619.utils.JwtUtil;
import com.g5619.utils.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author XXXXX-自己改
 * @since 2022-09-07
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     */
    @PostMapping("/login")
    public Telnet login(String username, String password){
        if (username == null || password == null) {
            return new Telnet().setCode(Telnet.CODE.AUTHENTICATIONERROR).setMsg("账号密码不能为空");
        }
        Subject subject = SecurityUtils.getSubject();
        String token = JwtUtil.createJWT(username, "back", "user", 1000 * 60 * 1);
        JwtToken jwtToken = new JwtToken(token, password);

        try {
            subject.login(jwtToken);
            return new Telnet().setCode(Telnet.CODE.OK).setMsg("成功").setData(token);
        } catch (IncorrectCredentialsException ice) {
            return new Telnet().setCode(Telnet.CODE.AUTHENTICATIONERROR).setMsg("密码不正确");
        } catch (UnknownAccountException uae) {
            return new Telnet().setCode(Telnet.CODE.AUTHENTICATIONERROR).setMsg("用户名不存在");
        }
    }

    /**
     * 注册
     */
    @PostMapping("/register")
    public Telnet register(String username, String password, String gender,
                           String email, String telephone){
        User seachUsername = userService.checkUserByName(username);
        if (null != seachUsername){
            return new Telnet().setCode(Telnet.CODE.DUPLICATIONDATA).setMsg("用户名已存在！");
        }

        User user = new User();
        user.setUsername(username);
        user.setType("user");
        user.setTelephone(telephone);
        user.setPassword(StringUtil.md5(password + username));
        user.setGender(gender);
        user.setEmail(email);
        user.setRoles("user");
        user.setPerms("user:visit");

        int i = userService.insertUser(user);
        if (i < 1)
            return new Telnet().setCode(Telnet.CODE.SQLERROR).setMsg("服务器异常!");
        return new Telnet().setCode(Telnet.CODE.OK).setMsg("操作成功！");
    }

    @GetMapping("/logout")
    public Telnet logout() {
        SecurityUtils.getSubject().logout();
        return new Telnet().setCode(Telnet.CODE.OK).setMsg("操作成功！");
    }

    /**
     * 获取用户个人信息列表
     */
    @GetMapping("myself")
    public Telnet myself(Long userId){
        return new Telnet().setCode(Telnet.CODE.OK).setData(userService.myself(userId)).setMsg("查询成功");
    }

    /**
     * 更新用户信息
     * @return
     */
    @PostMapping("/myself/update")
    public Telnet updateMyself(@RequestBody UpdateUserReq updateUserReq){
        int key = userService.updateMyself(updateUserReq);
        if (key>0){
            return new Telnet().setCode(Telnet.CODE.OK).setMsg("更新个人数据成功！");
        }
        return new Telnet().setCode(Telnet.CODE.SQLERROR).setMsg("查无此人!");
    }



    @GetMapping("/ab")
    @RequiresPermissions( value = {"user:visit","admin:manage"}, logical = Logical.OR)
    public Telnet ab(){
        return new Telnet().setCode(Telnet.CODE.OK).setMsg("测试ab");
    }

}
