package com.g5619.mapper;

import com.g5619.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.g5619.entity.vo.UserPreviousActivityVo;
import com.g5619.entity.vo.UserVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author XXXXX-自己改
 * @since 2022-09-07
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    List<UserVo> getAllUsers();

    List<UserPreviousActivityVo> getUserPreviousActivity(Long userId);

}
