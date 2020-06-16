package com.xiaoliu.learn.auth.handle;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description: 全局异常处理类
 * @author: liufb
 * @create: 2020/5/18 16:26
 **/
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandle {
    @ExceptionHandler
    @ResponseBody
    public String authorizationExceptionHandler(AuthorizationException e) {
        log.error("没有通过权限验证！", e);
        return "没有通过权限验证！";
    }
}
