package com.g5619.service.impl;

import com.g5619.entity.User;
import com.g5619.entity.res.UpdateUserReq;
import com.g5619.mapper.UserMapper;
import com.g5619.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserServiceImplTest {

    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;


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




    @Test
    void testupdateMyself() {
        //1.insert a new user
        User user = new User();
        user.setUsername("previoustestusername");
        user.setTelephone("4654");
        user.setPassword("previoustest@testmail.com");
        user.setGender("1");
        user.setEmail("previoustest@testmail.com");
        user.setRoles("1");
        user.setPerms("user:visit");
        user.setAge(66);
        user.setLevel(1);
        userMapper.insert(user);
        User user1 = userService.checkUserByName("previoustestusername");

        //2.update user information
        UpdateUserReq updateUserReq = new UpdateUserReq();
        updateUserReq.setUserId(user1.getUserId());
        updateUserReq.setUsername("testusername");
        updateUserReq.setPassword("testpassword");
        updateUserReq.setEmail("test@testmail.com");
        updateUserReq.setTelephone("15625");
        updateUserReq.setAge(99);
        int i = userService.updateMyself(updateUserReq);

        //3. Re-read the data once
        User updatemyself = userService.myself(user1.getUserId());

        //4.Make a comparison
        Assertions.assertEquals("testusername",updatemyself.getUsername(),"usernameupdatesuccess");
        Assertions.assertEquals("testpassword",updatemyself.getPassword(),"userPasswordupdatesuccess");
        Assertions.assertEquals("test@testmail.com",updatemyself.getEmail(),"userEmailsuccess");
        Assertions.assertEquals("15625",updatemyself.getTelephone(),"userTelephonesuccess");
        Assertions.assertEquals(99,updatemyself.getAge(),"userAgesuccess");

    }

    /**
     * test delete User
     */
    @Test
    void testDelUser() {
        //test the User is not in the database
        int i = userService.delUser(1L);
        Assertions.assertEquals(0, i);
        // test the User id was given wrong
        int i2 =  userService.delUser(-1L);
        Assertions.assertEquals(0, i2);
        //test the User is in the database
        User inputUser = new User();
        inputUser.setUsername("papi1");
        inputUser.setPassword("123");
        inputUser.setLevel(1);
        inputUser.setAge(22);
        inputUser.setEmail("770344012@qq.com");
        inputUser.setTelephone("123123");
        inputUser.setRoles("user");
        inputUser.setPerms("user:visit");
        userService.insertUser(inputUser);
        User user = userService.checkUserByName(inputUser.getUsername());
        int i3 =  userService.delUser(user.getUserId());
        Assertions.assertEquals(1,i3);
    }
}
