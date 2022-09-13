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
 * @author XXXXX-自己改
 * @since 2022-09-07
 */
public interface ActivityResultService extends IService<ActivityResult> {

    //组内排名
    List<RankVo> getGroupRankById(Long activityId);

}
