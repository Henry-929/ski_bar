package com.g5619.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserPreviousActivityVo {

    private Long activityId;
    private String name;
    private Long resultId;
    private Long userId;
    private Integer score;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;
    private Integer level;
    private String address;
    private String description;
    private Integer allPerson;
    private Integer remainPerson;
}
