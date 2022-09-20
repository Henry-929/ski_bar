package com.g5619.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalTime;
import java.util.Date;
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

    @TableId(value = "result_id", type = IdType.AUTO)
    private Long resultId;

    private Long activityId;

    private Long userId;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    private Integer score;


}
