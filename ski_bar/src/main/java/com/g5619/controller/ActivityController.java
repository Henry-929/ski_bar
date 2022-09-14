package com.g5619.controller;


//import com.g5619.config.MailService;
import com.g5619.config.Telnet;
import com.g5619.entity.Activity;
import com.g5619.entity.res.AddActivityReq;
import com.g5619.entity.vo.ActivityVo;
import com.g5619.service.ActivityService;
import com.g5619.service.GroupService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
     * 添加活动
     * Failed to convert value of type 'java.lang.String' to required type 'java.util.Date'
     */
    @PostMapping("/add")
    public Telnet addActivity(@RequestBody AddActivityReq addActivityReq){
        int key = activityService.addActivity(addActivityReq);
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



    /**
     * 管理员获得活动未审批列表
     */
    @GetMapping("UnApprovalList")
    public Telnet AdministratorUnApprovalList(){
        List<Activity> administratorUnApprovalList = activityService.getAdministratorUnApprovalList();
        if (administratorUnApprovalList !=null){
            return new Telnet().setCode(Telnet.CODE.OK).setMsg("查询成功").setData(administratorUnApprovalList);
        }
        return new Telnet().setCode(Telnet.CODE.NODATA).setMsg("所有活动都已审批");
    }

    /**
     * 加一个邮件通知事件
     * 管理员审批活动
     */
    @GetMapping("approval/{activityId}")
    public Telnet approvalActivity(@PathVariable Long activityId){
        int key = activityService.approvalActivity(activityId);
        if (key>0){
            return new Telnet().setCode(Telnet.CODE.OK).setMsg("修改成功，活动已被审批");
        }
        return new Telnet().setCode(Telnet.CODE.NODATA).setMsg("查无此活动");
    }

}
