package com.g5619.service;

import com.g5619.entity.ActivityRecords;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author XXXXX-自己改
 * @since 2022-09-07
 */
public interface ActivityRecordsService extends IService<ActivityRecords> {
    //退出活动
    int exitActivity(Long userId, Long activityId);

    //添加活动
    int addActivity(Long userId, Long activityId);

}
