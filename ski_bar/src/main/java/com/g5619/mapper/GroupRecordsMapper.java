package com.g5619.mapper;

import com.g5619.entity.GroupRecords;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author XXXXX-自己改
 * @since 2022-09-07
 */
@Repository
public interface GroupRecordsMapper extends BaseMapper<GroupRecords> {
    int deleteuseringroup(Long userId);

}
