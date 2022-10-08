package com.g5619.controller;



import com.g5619.config.Telnet;
import com.g5619.entity.Activity;
import com.g5619.entity.User;
import com.g5619.entity.vo.ActivityUserVo;
import com.g5619.entity.vo.UserPreviousActivityVo;
import com.g5619.service.ActivityRecordsService;
import com.g5619.service.EmailService;
import com.g5619.service.UserService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;

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
    @GetMapping("/add/{userId}/{activityId}")
    @RequiresPermissions( value = {"user:visit","admin:manage"}, logical = Logical.OR)
    public Telnet addActivity(@PathVariable("userId") Long userId, @PathVariable("activityId") Long activityId){
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
    @GetMapping("/exitactivity/{userId}/{activityId}")
    @RequiresPermissions( value = {"user:visit","admin:manage"}, logical = Logical.OR)
    public Telnet exitActivity(@PathVariable("userId") Long userId, @PathVariable("activityId") Long activityId) {
        int key = activityRecordsService.exitActivity(userId, activityId);
        if (key != -1) {
            return new Telnet().setCode(Telnet.CODE.OK).setMsg("退出活动成功");
        }
        return new Telnet().setCode(Telnet.CODE.NODATA).setMsg("你都不在这个活动当中玩你妈呢");
    }

    /**
     * 用户过往的活动记录
     */
    @GetMapping("/userpreviousactivityrecord/{userId}")
    @RequiresPermissions( value = {"user:visit","admin:manage"}, logical = Logical.OR)
    public Telnet userPreviousActivityRecord(@PathVariable("userId") Long userId){
        List<UserPreviousActivityVo> userPreviousActivity =
                userService.getUserPreviousActivity(userId);
        if (userPreviousActivity.size()>0){
            return new Telnet().setCode(Telnet.CODE.OK).setData(userPreviousActivity).setMsg("查询成功");
        }
        return new Telnet().setCode(Telnet.CODE.NODATA).setMsg("还没参加活动呢");
    }


    //d mlb
    /**
     * 获取活动中所有用户
     */
    @GetMapping("/activityuser/{activityId}")
    @RequiresPermissions("admin:manage")
    public Telnet Activityuser(@PathVariable("activityId") Long activityId){
        List<ActivityUserVo> activityuser = activityRecordsService.activityuser(activityId);
        if (activityuser.size()>0){
            return new Telnet().setCode(Telnet.CODE.OK).setData(activityuser).setMsg("查询成功");
        }
        return new Telnet().setCode(Telnet.CODE.NODATA).setMsg("还没有人");
    }
}
