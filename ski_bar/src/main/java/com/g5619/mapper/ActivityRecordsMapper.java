package com.g5619.mapper;

import com.g5619.entity.ActivityRecords;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.g5619.entity.vo.ActivityUserVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author XXXXX-自己改
 * @since 2022-09-07
 */
@Repository
public interface ActivityRecordsMapper extends BaseMapper<ActivityRecords> {
   int deleteactivityinactivityrecord(Long activityId);

   List<ActivityUserVo> activityuser(Long activityId);

}
