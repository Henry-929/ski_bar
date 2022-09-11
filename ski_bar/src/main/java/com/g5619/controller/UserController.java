package com.g5619.controller;


import com.g5619.config.Telnet;
import com.g5619.entity.User;
import com.g5619.service.UserService;
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
    @RequestMapping("/login")
    public Telnet login(String username, String password, String type){
        User user=userService.login(username,password,type);
        if (user!=null){
            return new Telnet().setCode(Telnet.CODE.OK).setMsg("success").setData(user);
        }
        return new Telnet().setCode(Telnet.CODE.NODATA).setMsg("Incorrect account or password or identity");
    }

    @PostMapping("/register")
    public Telnet register(String username, String password, String gender,
                           String email, String telephone, String type){
        User seachUsername = userService.searchUser(username);
        if (null != seachUsername){
            return new Telnet().setCode(Telnet.CODE.DUPLICATIONDATA).setMsg("用户名已存在！");
        }

        User user = new User();
        user.setUsername(username);
        user.setType(type);
        user.setTelephone(telephone);
        user.setPassword(password);
        user.setGender(gender);
        user.setEmail(email);

        int i = userService.insertUser(user);
        if (i < 1)
            return new Telnet().setCode(Telnet.CODE.SQLERROR).setMsg("服务器出错！");
        return new Telnet().setCode(Telnet.CODE.OK).setMsg("操作成功！");
    }

    @GetMapping("/ab")
    public Telnet ab(){
        return new Telnet().setCode(Telnet.CODE.OK).setMsg("测试ab");
    }

}
