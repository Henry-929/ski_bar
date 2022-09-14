package com.g5619.controller.admin;

import com.g5619.config.Telnet;
import com.g5619.entity.Activity;
import com.g5619.service.ActivityService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/admin")
@RequiresPermissions("admin:manage")
public class AdminController {

    @Autowired
    ActivityService activityService;

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

    @GetMapping("/ab")
    public Telnet adminAb(){
        return new Telnet().setCode(Telnet.CODE.OK).setMsg("测试管理员ab");
    }
}
