package com.g5619.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.g5619.entity.Activity;
import com.g5619.entity.ActivityRecords;
import com.g5619.entity.ActivityResult;
import com.g5619.entity.User;
import com.g5619.entity.vo.ActivityUserVo;
import com.g5619.mapper.ActivityMapper;
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
    @Autowired
    ActivityMapper activityMapper;

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
        Activity activity = activityMapper.selectById(activityId);
        activity.setRemainPerson(activity.getRemainPerson()-1);
        activityMapper.updateById(activity);
        //3.再把结果表删了
        QueryWrapper<ActivityResult> wrapperR = new QueryWrapper<>();
        wrapperR.eq("user_id",userId);
        wrapperR.eq("activity_id",activityId);
        return activityResultMapper.delete(wrapperR);
    }

    @Override
    public int addActivity(Long userId, Long activityId) {
        Activity activity = activityMapper.selectById(activityId);
        if (activity.getApprove()!=0){
            HashMap<String, Object> map = new HashMap<>();
            map.put("user_id",userId);
            map.put("activity_id",activityId);
            List<ActivityRecords> activityRecords = activityRecordsMapper.selectByMap(map);
            if (activityRecords.size()>0){
                return -2;//你已参加该活动
            }
            ActivityRecords activityRecords1 = new ActivityRecords();
            activityRecords1.setUserId(userId);
            activityRecords1.setActivityId(activityId);
            Activity activity1 = activityMapper.selectById(activityId);
            activity1.setRemainPerson(activity1.getRemainPerson()+1);
            activityMapper.updateById(activity1);
            return activityRecordsMapper.insert(activityRecords1);
        }
        return -1;//活动还未被审批
    }

    @Override
    public List<ActivityUserVo> activityuser(Long activityId) {
            return activityRecordsMapper.activityuser(activityId);
    }
}
