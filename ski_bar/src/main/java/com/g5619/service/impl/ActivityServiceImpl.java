package com.g5619.service.impl;

import com.g5619.entity.Activity;
import com.g5619.entity.vo.ActivityVo;
import com.g5619.mapper.ActivityMapper;
import com.g5619.service.ActivityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Override
    public int addActivity(String name, Date startTime, Date endTime, String address, int level,int allPerson) {
        Activity activity1 = new Activity();
        activity1.setName(name);
        HashMap<String, Object> map = new HashMap<>();
        map.put("name",name);
        List<Activity> activities = activityMapper.selectByMap(map);
        if (activities != null){
            return -1; //代表着重名，添加失败
        }else {
            Activity activityinsert = new Activity();
            activityinsert.setName(name);
            activityinsert.setAddress(address);
            activityinsert.setStartTime(startTime);
            activityinsert.setEndTime(endTime);
            activityinsert.setLevel(level);
            activityinsert.setAllPerson(allPerson);
            activityinsert.setRemainPerson(0);
            activityinsert.setState(0);
            activityinsert.setApprove(0);
            activityMapper.insert(activityinsert);
            return 1;// 插入成功
        }
    }


    /**
     * 查询未授权活动
     * @return
     */
    @Override
    public List<Activity> getAdministratorUnApprovalList() {
        Activity activity = new Activity();
        activity.setApprove(0);
        HashMap<String, Object> map = new HashMap<>();
        map.put("approve",0);
        List<Activity> unapproveactivities = activityMapper.selectByMap(map);
        return unapproveactivities;
    }


    /**
     * 审批活动
     * @param activityId
     * @return
     */
    @Override
    public int approvalActivity(Long activityId) {
        Activity activity = activityMapper.selectById(activityId);
        if (activity !=null){
            activity.setApprove(1);
            return 1;//成功修改
        }
        return -1;//查无数据
    }
}
