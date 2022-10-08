package com.g5619.controller;


//import com.g5619.config.MailService;
import com.g5619.config.Telnet;
import com.g5619.entity.Activity;
import com.g5619.entity.res.CreateActivityReq;
import com.g5619.entity.vo.ActivityVo;
import com.g5619.service.ActivityService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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


    //a mlb
    /**
     * 根据活动id获取活动详情
     */
    @GetMapping("/detail/{activityId}")
    @RequiresPermissions( value = {"user:visit","admin:manage"}, logical = Logical.OR)
    public Telnet getActivityDetailById(@PathVariable Long activityId){
        Activity activity = activityService.getActivityDetailById(activityId);
        if (activity!=null){
            return new Telnet().setCode(Telnet.CODE.OK).setMsg("查询成功").setData(activity);
        }
        return new Telnet().setCode(Telnet.CODE.NODATA).setMsg("查无此活动？");
    }


    /**
     * 修改活动
     */
    @PostMapping("/edit")
    @RequiresPermissions("admin:manage")
    public Telnet editActivity(@RequestBody ActivityVo activityVo){
        int key = activityService.editActivity(activityVo);
        if(key>0){
            return new Telnet().setCode(Telnet.CODE.OK).setMsg("活动更新成攻");
        }
        return new Telnet().setCode(Telnet.CODE.SQLERROR).setMsg("数据库出错");
    }


    //b mlb
    /**
     * 活动展示
     */
    @GetMapping("/list")
    @RequiresPermissions( value = {"user:visit","admin:manage"}, logical = Logical.OR)
    public Telnet activityList(){
        List<ActivityVo> activityList = activityService.getActivityList();
        if (activityList.size()>0){
            return new Telnet().setCode(Telnet.CODE.OK).setMsg("查询成功").setData(activityList);
        }
        return new Telnet().setCode(Telnet.CODE.NODATA).setMsg("当前还没有活动");
    }

    /**
     * 创建活动
     */
    @PostMapping("/create")
    @RequiresPermissions( value = {"user:visit","admin:manage"}, logical = Logical.OR)
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
    @GetMapping("/search/{keywords}")
    @RequiresPermissions( value = {"user:visit","admin:manage"}, logical = Logical.OR)
    public Telnet searchActivies(@PathVariable("keywords") String keywords){
        List<Activity> activities = activityService.searchActivies(keywords);
        if (activities !=null){
            return new Telnet().setCode(Telnet.CODE.OK).setData(activities).setMsg("查找成功！");
        }
        return new Telnet().setCode(Telnet.CODE.SQLERROR).setMsg("系统繁忙");
    }


    //c mlb
    /**
     * 删除活动
     */
    @GetMapping("/{activityId}")
    @RequiresPermissions("admin:manage")
    public Telnet delActivity(@PathVariable Long activityId){
        return new Telnet().setCode(Telnet.CODE.OK)
                            .setData(activityService.delActivity(activityId)).setMsg("删除成功");
    }





}
