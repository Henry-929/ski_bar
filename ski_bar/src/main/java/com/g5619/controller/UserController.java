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

import java.util.HashMap;

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
    public Telnet login(@RequestBody User user){
        if (user.getUsername() == null || user.getPassword() == null) {
            return new Telnet().setCode(Telnet.CODE.AUTHENTICATIONERROR).setMsg("账号密码不能为空");
        }
        Subject subject = SecurityUtils.getSubject();
        String token = JwtUtil.createJWT(user.getUsername(), "back", "user", 1000 * 60 * 30);
        JwtToken jwtToken = new JwtToken(token, user.getPassword());
        HashMap<String, Object> map = new HashMap<>();
        try {
            subject.login(jwtToken);

            User userFromDB = (User) subject.getPrincipal();
            System.out.println("userFromDB is "+userFromDB.toString());
            map.put("token", token);
            map.put("user", userFromDB);
            return new Telnet().setCode(Telnet.CODE.OK).setMsg("成功").setData(map);
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
    public Telnet register(@RequestBody User userAccount){
        User seachUsername = userService.checkUserByName(userAccount.getUsername());
        if (null != seachUsername)
            return new Telnet().setCode(Telnet.CODE.DUPLICATIONDATA).setMsg("用户名已存在！");

        User user = new User();
        user.setUsername(userAccount.getUsername());
        user.setTelephone(userAccount.getTelephone());
        user.setPassword(StringUtil.md5(userAccount.getPassword() + "aasd123viav9"));
        user.setGender(userAccount.getGender());
        user.setEmail(userAccount.getEmail());
        user.setRoles("user");
        user.setPerms("user:visit");
        user.setAge(userAccount.getAge());
        user.setLevel(userAccount.getLevel());

        int i = userService.insertUser(user);
        if (i < 1)
            return new Telnet().setCode(Telnet.CODE.SQLERROR).setMsg("服务器异常!");
        return new Telnet().setCode(Telnet.CODE.OK).setMsg("操作成功！");
    }

    @GetMapping("/logout")
    @RequiresPermissions( value = {"user:visit","admin:manage"}, logical = Logical.OR)
    public Telnet logout() {
        SecurityUtils.getSubject().logout();
        return new Telnet().setCode(Telnet.CODE.OK).setMsg("操作成功！");
    }


    //e mlb
    /**
     * 获取用户个人信息列表
     */
    @GetMapping("/myself")
    @RequiresPermissions( value = {"user:visit","admin:manage"}, logical = Logical.OR)
    public Telnet myself(Long userId){
        return new Telnet().setCode(Telnet.CODE.OK).setData(userService.myself(userId)).setMsg("查询成功");
    }

    /**
     * 更新用户信息
     * @return
     */
    @PostMapping("/myself/update")
    @RequiresPermissions( value = {"user:visit","admin:manage"}, logical = Logical.OR)
    public Telnet updateMyself(@RequestBody UpdateUserReq updateUserReq){
        int key = userService.updateMyself(updateUserReq);
        if (key>0){
            return new Telnet().setCode(Telnet.CODE.OK).setMsg("更新个人数据成功！");
        }
        return new Telnet().setCode(Telnet.CODE.SQLERROR).setMsg("查无此人!");
    }

//    /**
//     * 用户自己的比赛
//     * @param userId
//     * @return
//     */
//    @PostMapping("myrank")
//    public Telnet myCompetition(Long userId){
//
//    }

    @GetMapping("/ab")
    @RequiresPermissions( value = {"user:visit","admin:manage"}, logical = Logical.OR)
    public Telnet ab(){
        return new Telnet().setCode(Telnet.CODE.OK).setMsg("测试ab");
    }

}
