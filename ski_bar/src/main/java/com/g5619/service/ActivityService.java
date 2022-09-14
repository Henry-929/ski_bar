package com.g5619.service;

import com.g5619.entity.Activity;
import com.baomidou.mybatisplus.extension.service.IService;
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

    //活动列表
    List<ActivityVo> getActivityList();

    //活动详情
    Activity getActivityDetailById(Long activityId);

}
