package com.g5619;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.g5619.entity.User;
import com.g5619.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
class SkiBarApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void testFindAll() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", "vivo");
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
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
