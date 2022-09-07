package com.g5619.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Zheyin Zhang
 * @since 2022-09-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ActivityResult implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "result_id", type = IdType.ASSIGN_ID)
    private Long resultId;

    private Long activityId;

    private Long userId;

    private Date time;

    private Integer score;


}
