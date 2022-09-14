package com.g5619.controller;


//import com.g5619.config.MailService;
import com.g5619.config.Telnet;
import com.g5619.entity.Activity;
import com.g5619.entity.res.CreateActivityReq;
import com.g5619.entity.vo.ActivityVo;
import com.g5619.service.ActivityService;
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
     * 根据活动id获取活动详情
     */
    @GetMapping("detail/{activityId}")
    public Telnet getActivityDetailById(@PathVariable Long activityId){
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

    /**
     * 创建活动
     */
    @PostMapping("/create")
    public Telnet createActivity(@RequestBody CreateActivityReq createActivityReq){
        int key = activityService.createActivity(createActivityReq);
        if (key>0){
            return new Telnet().setCode(Telnet.CODE.OK).setMsg("添加成功");
        }else if (key==-1){
            return new Telnet().setCode(Telnet.CODE.ARTIFICIAL).setMsg("查询到有相同名字的活动，请换个名字重新添加");
        }
        return new Telnet().setCode(Telnet.CODE.SQLERROR).setMsg("系统繁忙");
    }

    /**
     * 查询活动
     */
    @PostMapping("/search")
    public Telnet searchActivies(String keywords){
        List<Activity> activities = activityService.searchActivies(keywords);
        if (activities !=null){
            return new Telnet().setCode(Telnet.CODE.OK).setData(activities).setMsg("查找成功！");
        }
        return new Telnet().setCode(Telnet.CODE.SQLERROR).setMsg("系统繁忙");
    }





}
