package com.g5619.entity.res;

import lombok.Data;

@Data
public class UpdateUserReq {

    private Long userId;
    private String username;
    private String password;
    private String email;
    private String telephone;

    private int age;
}
