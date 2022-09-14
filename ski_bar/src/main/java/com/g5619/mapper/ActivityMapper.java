package com.g5619.mapper;

import com.g5619.entity.Activity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.g5619.entity.vo.ActivityVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Ruize Chen
 * @since 2022-09-13
 */
@Repository
public interface ActivityMapper extends BaseMapper<Activity> {
    List<ActivityVo> getActivityList();


}
