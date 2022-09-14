package com.g5619.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.g5619.entity.Activity;
import com.g5619.entity.res.AddActivityReq;
import com.g5619.entity.vo.ActivityVo;
import com.g5619.mapper.ActivityMapper;
import com.g5619.service.ActivityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

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
    public List<Activity> searchActivies(String keywords) {
        QueryWrapper<Activity> activityQueryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(keywords)){
            activityQueryWrapper.lambda()
                    .like(Activity::getActivityId,keywords).or()
                    .like(Activity::getAddress,keywords).or()
                    .like(Activity::getName,keywords);
        }
        return activityMapper.selectList(activityQueryWrapper);
    }

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
    public int addActivity(AddActivityReq addActivityReq) {
        Activity activity1 = new Activity();
        activity1.setName(addActivityReq.getName());

        HashMap<String, Object> map = new HashMap<>();
        map.put("name",addActivityReq.getName());

        List<Activity> activities = activityMapper.selectByMap(map);

        if (activities.size()>0){
            return -1; //代表着重名，添加失败
        }else {
            Activity activityinsert = new Activity();
            activityinsert.setName(addActivityReq.getName());
            activityinsert.setAddress(addActivityReq.getAddress());

            activityinsert.setStartTime(addActivityReq.getStartTime());
            activityinsert.setEndTime(addActivityReq.getEndTime());


            activityinsert.setLevel(addActivityReq.getLevel());
            activityinsert.setAllPerson(addActivityReq.getAllPerson());
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
