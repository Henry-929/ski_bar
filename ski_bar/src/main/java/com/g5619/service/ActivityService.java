package com.g5619.service;

import com.g5619.entity.Activity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.g5619.entity.res.CreateActivityReq;
import com.g5619.entity.vo.ActivityVo;

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

    //查询活动
    List<Activity> searchActivies(String keywords);

    //活动列表
    List<ActivityVo> getActivityList();

    //活动详情
    Activity getActivityDetailById(Long activityId);

    //创建活动
    int createActivity(CreateActivityReq createActivityReq);

    //管理员获得活动未审批列表
    List<Activity> getAdministratorUnApprovalList();

    //管理员审批活动
    int approvalActivity(Long activityId);

    //删除活动
    int delActivity(Long activityId);

    //修改活动
    int editActivity(ActivityVo activityVo);

}
