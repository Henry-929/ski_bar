package com.g5619.service;

import com.g5619.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.g5619.entity.res.UpdateUserReq;
import com.g5619.entity.vo.UserActivityVo;
import com.g5619.entity.vo.UserPreviousActivityVo;
import com.g5619.entity.vo.UserVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author XXXXX-自己改
 * @since 2022-09-07
 */
public interface UserService extends IService<User> {

//    User login(String username, String password);

    int insertUser(User user);

    User checkUserByName(String username);

    //获取用户信息
    User myself(Long userId);

    //更新用户
    int updateMyself(UpdateUserReq updateUserReq);

    //获取所有用户信息列表
    List<UserVo> getAllUsers();

    //删除用户
    int delUser(Long userId);

    //显示用户
    List<UserPreviousActivityVo> getUserPreviousActivity(Long userId);


}
