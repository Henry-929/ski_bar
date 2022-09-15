package com.g5619.controller;



import com.g5619.config.Telnet;
import com.g5619.entity.Activity;
import com.g5619.entity.User;
import com.g5619.service.ActivityRecordsService;
import com.g5619.service.EmailService;
import com.g5619.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author XXXXX-自己改
 * @since 2022-09-07
 */
@RestController
@RequestMapping("/activity-records")
public class ActivityRecordsController {

    @Autowired
    ActivityRecordsService activityRecordsService;
    @Autowired
    UserService userService;
    @Autowired
    EmailService emailService;


    /**
     * 用户参加活动组
     */
    @GetMapping("/add")
    public Telnet addActivity(Long userId, Long activityId){
        int key = activityRecordsService.addActivity(userId, activityId);
        User user = userService.getById(userId);
        if (key>0){
            emailService.joinin(user.getEmail());
            return new Telnet().setCode(Telnet.CODE.OK).setMsg("活动参加成功");
        }else if (key==-1){
            return new Telnet().setCode(Telnet.CODE.ARTIFICIAL).setMsg("该活动还未被审批，请过些时候来看看");
        }
        return new Telnet().setCode(Telnet.CODE.SQLERROR).setMsg("你已参加该活动，请勿重复操作");


    }

    /**
     * 用户退出活动组
     * @return
     */
    @GetMapping("exitactivity")
    public Telnet exitActivity(Long userId, Long activityId) {
        int key = activityRecordsService.exitActivity(userId, activityId);
        if (key > 0) {
            return new Telnet().setCode(Telnet.CODE.OK).setMsg("退出活动成功");
        }
        return new Telnet().setCode(Telnet.CODE.NODATA).setMsg("你都不在这个活动当中玩你妈呢");
    }
}
