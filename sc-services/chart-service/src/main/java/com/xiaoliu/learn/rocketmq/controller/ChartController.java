package com.xiaoliu.learn.rocketmq.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leyangjie
 * @date 2019/1/16 10:17
 * @Description：图标Controller
 */
@RestController
@RequestMapping("/chart")
@Api(tags = "图表微服务", description = "图表微服务相关api")
public class ChartController {

    @PostMapping("/test/swagger")
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
