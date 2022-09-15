package com.g5619.service;

import com.g5619.entity.ActivityRecords;
import com.g5619.entity.ActivityResult;
import com.baomidou.mybatisplus.extension.service.IService;
import com.g5619.entity.vo.RankVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Ruize Chen
 * @since 2022-09-13
 */
public interface ActivityResultService extends IService<ActivityResult> {

    //组内排名
    List<RankVo> getGroupRankById(Long activityId);



}
