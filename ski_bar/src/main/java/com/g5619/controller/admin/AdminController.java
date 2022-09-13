package com.g5619.controller.admin;

import com.g5619.config.Telnet;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/ab")
    public Telnet adminAb(){
        return new Telnet().setCode(Telnet.CODE.OK).setMsg("测试管理员ab");
    }
}
