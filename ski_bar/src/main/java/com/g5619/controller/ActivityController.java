package com.g5619.controller;


//import com.g5619.config.MailService;
import com.g5619.config.Telnet;
import com.g5619.entity.Activity;
import com.g5619.entity.vo.ActivityVo;
import com.g5619.service.ActivityService;
import com.g5619.service.GroupService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Ruize Chen
 * @since 2022-09-13
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



    /**
     * 活动展示
     */
    @GetMapping("/list")
    public Telnet activityList(){
        List<ActivityVo> activityList = activityService.getActivityList();
        if (activityList!=null){
            return new Telnet().setCode(Telnet.CODE.OK).setMsg("查询成功").setData(activityList);
        }
        return new Telnet().setCode(Telnet.CODE.NODATA).setMsg("当前还没有活动");

    }

}
