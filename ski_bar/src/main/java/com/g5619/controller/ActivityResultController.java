package com.g5619.controller;


import com.g5619.config.Telnet;
import com.g5619.entity.res.GradeReq;
import com.g5619.entity.vo.RankVo;
import com.g5619.service.ActivityRecordsService;
import com.g5619.service.ActivityResultService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
@RequestMapping("/activity-result")
public class ActivityResultController {

    @Autowired
    ActivityResultService activityRecordsService;

    /**
     * 组内排名
     */
    @GetMapping("/rank")
    @ResponseBody
    @RequiresPermissions( value = {"user:visit","admin:manage"}, logical = Logical.OR)
    public Telnet getGroupRankById(Long activityId){
        List<RankVo> groupRank= activityRecordsService.getGroupRankById(activityId);
        if (groupRank!=null){
            return new Telnet().setCode(Telnet.CODE.OK).setMsg("查询成功").setData(groupRank);
        }
        return new Telnet().setCode(Telnet.CODE.NODATA).setMsg("该活动还未有排名");
    }

    /**
     * 添加成绩
     */
    @PostMapping("/grade")
    @RequiresPermissions("admin:manage")
    public Telnet addGrade(@RequestBody List<GradeReq> gradeList){
        if (activityRecordsService.addGrade(gradeList)){
            return new Telnet().setCode(Telnet.CODE.OK).setMsg("插入成功");
        }
        return new Telnet().setCode(Telnet.CODE.NODATA).setMsg("插入失败");
    }





}
