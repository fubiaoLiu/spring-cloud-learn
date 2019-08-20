package com.xiaoliu.learn.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: Sentinel
 * @author: FuBiaoLiu
 * @date: 2019/7/26
 */
@RestController
@RequestMapping("/sentinel")
@Api(tags = "Sentinel")
public class SentinelController {

    @PostMapping("/test/swagger")
    @SentinelResource("/test/swagger")
    @ResponseBody
    @ApiOperation(value = "测试swagger", notes = "测试swagger")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "bdate", dataType = "String", value = "开始时间"),
            @ApiImplicitParam(paramType = "query", name = "edate", dataType = "String", value = "结束时间")
    })
    public String testSwagger(){
        return "hello swagger";
    }
}
