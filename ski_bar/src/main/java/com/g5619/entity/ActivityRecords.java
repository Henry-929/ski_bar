package com.g5619.entity;

import com.baomidou.mybatisplus.annotation.*;

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
public class ActivityRecords implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "record_id", type = IdType.AUTO)
    private Long recordId;

    private Long activityId;

    private Long userId;

    @TableField(fill = FieldFill.INSERT)
    private Date time;



}
