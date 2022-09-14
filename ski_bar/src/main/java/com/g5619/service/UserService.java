package com.g5619.service;

import com.g5619.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.g5619.entity.res.UpdateUserReq;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author XXXXX-自己改
 * @since 2022-09-07
 */
public interface UserService extends IService<User> {

    //登录
    User login(String username, String password,String type);

    int insertUser(User user);

    User checkUserByName(String username);

    //获取用户信息
    User myself(Long userId);

    //更新用户
    int updateMyself(UpdateUserReq updateUserReq);

}
