package com.g5619.service;

import com.g5619.entity.Activity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.g5619.entity.vo.ActivityVo;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Ruize Chen
 * @since 2022-09-13
 */
public interface ActivityService extends IService<Activity> {

    //活动列表
    List<ActivityVo> getActivityList();

    //活动详情
    Activity getActivityDetailById(Long activityId);

    //添加活动
    int addActivity(String name, Date startTime, Date endTime, String address, int level,int allPerson);

    //管理员获得活动未审批列表
    List<Activity> getAdministratorUnApprovalList();

    //管理员审批活动
    int approvalActivity(Long activityId);

}
