package com.g5619.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.g5619.entity.ActivityRecords;
import com.g5619.entity.ActivityResult;
import com.g5619.entity.User;
import com.g5619.mapper.ActivityRecordsMapper;
import com.g5619.mapper.ActivityResultMapper;
import com.g5619.service.ActivityRecordsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.g5619.service.ActivityResultService;
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
public class ActivityRecordsServiceImpl extends ServiceImpl<ActivityRecordsMapper, ActivityRecords> implements ActivityRecordsService {

    @Autowired
    ActivityRecordsMapper activityRecordsMapper;
    @Autowired
    ActivityResultMapper activityResultMapper;

    @Override
    public int exitActivity(Long userId, Long activityId) {
        //1.userid去查activityid和activityid去匹配，有结果继续，无结果不在此活动不用退
        QueryWrapper<ActivityRecords> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        wrapper.eq("activity_id",activityId);
        List<ActivityRecords> activityRecords = activityRecordsMapper.selectList(wrapper);
        //2.在此活动先把记录表删了
        if (activityRecords.size()>0){
            activityRecordsMapper.delete(wrapper);
        } else{
            return -1;//都不在此活动
        }
        //3.再把结果表删了
        QueryWrapper<ActivityResult> wrapperR = new QueryWrapper<>();
        wrapperR.eq("user_id",userId);
        wrapperR.eq("activity_id",activityId);
        return activityResultMapper.delete(wrapperR);
    }
}
