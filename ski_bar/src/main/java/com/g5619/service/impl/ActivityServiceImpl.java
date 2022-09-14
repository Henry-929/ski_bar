package com.g5619.service.impl;

import com.g5619.entity.Activity;
import com.g5619.entity.vo.ActivityVo;
import com.g5619.mapper.ActivityMapper;
import com.g5619.service.ActivityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ruize Chen
 * @since 2022-09-13
 */
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {

    @Autowired
    ActivityMapper activityMapper;

    @Override
    public List<ActivityVo> getActivityList() {
        List<ActivityVo> activityList = activityMapper.getActivityList();
        return activityList;
    }

    @Override
    public Activity getActivityDetailById(Long activityId) {
        return activityMapper.selectById(activityId);
    }
}
