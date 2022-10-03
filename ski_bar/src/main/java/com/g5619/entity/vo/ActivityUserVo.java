package com.g5619.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class ActivityUserVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private String username;

    private String gender;

    private String email;

    private String telephone;

    private int age;

    private int level;
}
