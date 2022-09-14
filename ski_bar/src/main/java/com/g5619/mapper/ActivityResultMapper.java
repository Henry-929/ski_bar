package com.g5619.mapper;

import com.g5619.entity.ActivityResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.g5619.entity.vo.RankVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Ruize Chen
 * @since 2022-09-13
 */
@Repository
public interface ActivityResultMapper extends BaseMapper<ActivityResult> {

    List<RankVo> getGroupRankById(@Param("activityId") Long activityId);
}
