package com.g5619.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.g5619.entity.Activity;
import com.g5619.entity.ActivityRecords;
import com.g5619.entity.ActivityResult;
import com.g5619.entity.res.GradeReq;
import com.g5619.entity.vo.RankVo;
import com.g5619.entity.vo.UserActivityVo;
import com.g5619.mapper.ActivityRecordsMapper;
import com.g5619.mapper.ActivityResultMapper;
import com.g5619.service.ActivityResultService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
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

    @Override
    public List<UserActivityVo> myCompetition(Long userId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("user_id",userId);
        List<ActivityResult> activityResults = activityResultMapper.selectByMap(map);

        return null;
    }

    @Override
    public boolean addGrade(List<GradeReq> gradeList) {
        List<ActivityResult> activityResults = new ArrayList<>();

        for (GradeReq gradeReq : gradeList) {
            ActivityResult activityResult = new ActivityResult();
            activityResult.setResultId(gradeReq.getResultId());
            activityResult.setActivityId(gradeReq.getActivityId());
            activityResult.setUserId(gradeReq.getUserId());
            activityResult.setScore(gradeReq.getScore());
            activityResults.add(activityResult);
        }
        return this.saveOrUpdateBatch(activityResults);

    }
}
