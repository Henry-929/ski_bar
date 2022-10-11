package com.g5619;

import com.g5619.entity.User;
import com.g5619.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SkiBarApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void testFindAll() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    void testLogInsert() {
        User user = new User();
        user.setUsername("oppo");
        user.setTelephone("123sd");
        user.setPassword("qwe");
        user.setGender("0");
        user.setEmail("7702024@gmail");
        int insert = userMapper.insert(user);
        System.out.println("insert result is :"+insert);
    }

}
