package com.g5619.service.impl;

import com.g5619.entity.User;
import com.g5619.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sun.jvm.hotspot.utilities.Assert;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Test
    void testRegister() {
        User inputUser = new User();
        inputUser.setUsername("papi");
        inputUser.setPassword("123");
        inputUser.setLevel(1);
        inputUser.setAge(22);
        inputUser.setEmail("770344012@qq.com");
        inputUser.setTelephone("123123");
        inputUser.setRoles("user");
        inputUser.setPerms("user:visit");

        User seachUsername = userService.checkUserByName(inputUser.getUsername());
        Assertions.assertEquals(null, seachUsername,"查看用户是否已存在");

        int i = userService.insertUser(inputUser);
        Assertions.assertEquals(1, i,"返回1，表示插入数据成功");

        User actualUser = userService.checkUserByName(inputUser.getUsername());
        inputUser.setCreateTime(null);
        actualUser.setCreateTime(null);
        Assertions.assertEquals(inputUser, actualUser,"查看是否注册成功");

    }

}