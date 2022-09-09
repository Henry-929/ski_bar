package com.g5619.controller;


import com.g5619.config.Telnet;
import com.g5619.entity.User;
import com.g5619.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Target;

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
    @RequestMapping("login")
    @ResponseBody
    public Telnet login(String username, String password, String type){
        User user=userService.login(username,password,type);
        if (user!=null){
            return new Telnet().setCode(Telnet.CODE.OK).setMsg("success").setData(user);
        }
        return new Telnet().setCode(Telnet.CODE.NODATA).setMsg("Incorrect account or password or identity");
    }




}
