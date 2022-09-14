package com.g5619.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
 * <p>
 *
 * </p>
 *
 * @author Ruize Chen
 * @since 2022-09-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ActivityVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "activity_id", type = IdType.ASSIGN_ID)

    private Long activityId;

    private String name;

    private String description;

    private int level;

    private String address;

    private Integer allPerson;

    private Integer remainPerson;

}
