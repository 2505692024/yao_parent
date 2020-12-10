package com.yao.eduservice.controller;

import com.yao.commonutils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author yaoheng
 * @date 2020/12/5 22:07
 */
@Api(value = "登录接口")
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class EduLoginTeacher {
    @ApiOperation(value = "登录接口")
    @PostMapping("/login")
    public Result login(){
        return Result.ok().data("token","admin");
    }
    @ApiOperation(value = "获取用户信息")
    @GetMapping("/loginInfo")
    public Result loginInfo(){
        return Result.ok().data("roles","[admin]").data("name","admin").data("avatar","https://guli-file-190513.oss-cn-beijing.aliyuncs.com/avatar/default.jpg");
    }
}
