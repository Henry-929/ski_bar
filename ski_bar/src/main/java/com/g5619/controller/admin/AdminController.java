package com.g5619.controller.admin;

import com.g5619.config.Telnet;
import com.g5619.entity.Activity;
import com.g5619.entity.User;
import com.g5619.entity.vo.UserVo;
import com.g5619.service.ActivityService;
import com.g5619.service.EmailService;
import com.g5619.service.UserService;
import com.g5619.utils.Email;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.*;

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
//@RequiresPermissions("admin:manage")
public class AdminController {

    @Autowired
    ActivityService activityService;
    @Autowired
    UserService userService;
    @Autowired
    EmailService emailService;
    /**
     * 管理员获得活动未审批列表
     */
    @GetMapping("UnApprovalList")
    @RequiresPermissions("admin:manage")
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
    @RequiresPermissions("admin:manage")
    public Telnet approvalActivity(@PathVariable Long activityId){
        int key = activityService.approvalActivity(activityId);
        Activity activityDetailById = activityService.getActivityDetailById(activityId);
        User user = userService.getById(activityDetailById.getUserId());
        if (key>0){
            emailService.contextLoads(user.getEmail());
            return new Telnet().setCode(Telnet.CODE.OK).setMsg("修改成功，活动已被审批");
        }
        return new Telnet().setCode(Telnet.CODE.NODATA).setMsg("查无此活动");
    }


    /**
     * 获取所有用户信息列表
     * @return
     */
    @GetMapping("allusers")
    @RequiresPermissions("admin:manage")
    public Telnet getAllUsers(){
        List<UserVo> allUsers = userService.getAllUsers();
        if (allUsers.size()>0){
            return new Telnet().setCode(Telnet.CODE.OK).setData(allUsers).setMsg("查询成功");
        }
        return new Telnet().setCode(Telnet.CODE.NODATA).setMsg("还没有用户");
    }

    //f mlb
    /**
     * 删除用户
     * @return
     */
    @GetMapping("/{userId}")
    @RequiresPermissions("admin:manage")
    public Telnet delUser(@PathVariable Long userId){
        int key = userService.delUser(userId);
        if (key >0){
            return new Telnet().setCode(Telnet.CODE.OK).setMsg("删除成功");
        }
        return new Telnet().setCode(Telnet.CODE.SQLERROR).setMsg("查无此人!");
    }

    @GetMapping("/ab")
    @RequiresPermissions("admin:manage")
    public Telnet adminAb(){
        return new Telnet().setCode(Telnet.CODE.OK).setMsg("测试管理员ab");
    }



}
