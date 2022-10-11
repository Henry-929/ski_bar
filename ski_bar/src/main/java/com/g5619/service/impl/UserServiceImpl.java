package com.g5619.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.g5619.entity.GroupRecords;
import com.g5619.entity.User;
import com.g5619.entity.res.UpdateUserReq;
import com.g5619.entity.vo.UserActivityVo;
import com.g5619.entity.vo.UserPreviousActivityVo;
import com.g5619.entity.vo.UserVo;
import com.g5619.mapper.GroupRecordsMapper;
import com.g5619.mapper.UserMapper;
import com.g5619.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author XXXXX-自己改
 * @since 2022-09-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    GroupRecordsMapper groupRecordsMapper;

    @Override
    public int insertUser(User user) {
        int insert = userMapper.insert(user);
        return insert;
    }

    @Override
    public User checkUserByName(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        User user = userMapper.selectOne(wrapper);
        return user;
    }

    @Override
    public User myself(Long userId) {
        return userMapper.selectById(userId);
    }

    @Override
    public int updateMyself(UpdateUserReq updateUserReq) {
        User user = userMapper.selectById(updateUserReq.getUserId());
        if (user !=null){
            User user1 = new User();
            BeanUtils.copyProperties(updateUserReq,user1);
            return userMapper.updateById(user1);//更新个人数据成功
        }
        return -1; //查无此人
    }

    @Override
    public List<UserVo> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @Override
    public int delUser(Long userId) {
            return userMapper.deleteById(userId);
    }

    @Override
    public List<UserPreviousActivityVo> getUserPreviousActivity(Long userId) {
        return userMapper.getUserPreviousActivity(userId);
    }

    //@Override
//    public User login(String username, String password) {
//        HashMap<String, Object> map = new HashMap<>();
//        // 自定义用户查询
//        map.put("username",username);
//        map.put("password",password);
//        List<User> users = userMapper.selectByMap(map);
//        if (users.size()>0){
//            return users.get(0);
//        }
//        return null;
//    }

}
