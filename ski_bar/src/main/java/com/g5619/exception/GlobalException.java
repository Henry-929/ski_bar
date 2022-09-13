package com.g5619.exception;

import com.g5619.config.Telnet;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestController
@RestControllerAdvice
public class GlobalException {

    @GetMapping("/noAuthorization")
    public Telnet noAuthorization() {
        return new Telnet().setCode(Telnet.CODE.AUTHENTICATIONERROR).setMsg("用户无权限操作");
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    public Telnet handler(UnauthorizedException e) {
        log.error("运行时异常：----------------{}", e.getMessage());
        return new Telnet().setCode(Telnet.CODE.AUTHENTICATIONERROR).setMsg("用户无权限操作");
    }

    @ExceptionHandler(value = ExpiredCredentialsException.class)
    public Telnet handler(ExpiredCredentialsException e) {
        log.error("运行时异常：----------------{}", e.getMessage());
        return new Telnet().setCode(Telnet.CODE.AUTHENTICATIONERROR).setMsg("登录已过期，请重新登录");
    }

    @ExceptionHandler(value = UnauthenticatedException.class)
    public Telnet handler(UnauthenticatedException e) {
        log.error("运行时异常：----------------{}", "未登录异常捕获");
        return new Telnet().setCode(Telnet.CODE.AUTHENTICATIONERROR).setMsg("未登录,请先登录");
    }
}
