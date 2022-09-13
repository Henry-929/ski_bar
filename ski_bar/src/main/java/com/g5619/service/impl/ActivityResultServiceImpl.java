package com.g5619.service.impl;

import com.g5619.entity.ActivityRecords;
import com.g5619.entity.ActivityResult;
import com.g5619.entity.vo.RankVo;
import com.g5619.mapper.ActivityRecordsMapper;
import com.g5619.mapper.ActivityResultMapper;
import com.g5619.service.ActivityResultService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class ActivityResultServiceImpl extends ServiceImpl<ActivityResultMapper, ActivityResult> implements ActivityResultService {

    @Autowired
    ActivityResultMapper activityResultMapper;

    @Override
    public List<RankVo> getGroupRankById(Long activityId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("activity_id",activityId);
        List<RankVo> rank = activityResultMapper.getGroupRankById(activityId);
        return rank;
    }
}
