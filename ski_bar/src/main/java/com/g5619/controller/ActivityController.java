package com.g5619.controller;


import com.g5619.config.Telnet;
import com.g5619.entity.Activity;
import com.g5619.service.ActivityService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author XXXXX-自己改
 * @since 2022-09-07
 */
@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    ActivityService activityService;

    /**
     * 活动详情
     */
    @GetMapping("/detail")
    public Telnet getActivityDetailById(Long activityId){
        Activity activity = activityService.getActivityDetailById(activityId);
        if (activity!=null){
            return new Telnet().setCode(Telnet.CODE.OK).setMsg("查询成功").setData(activity);
        }
        return new Telnet().setCode(Telnet.CODE.NODATA).setMsg("查无此活动？");

    }

}
