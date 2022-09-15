package com.g5619.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserActivityVo {

    private Long activityId;

    private Long userId;

    private String name;

    private String description;

    private int level;

    private String address;

    private Integer allPerson;

    private Integer remainPerson;
}
