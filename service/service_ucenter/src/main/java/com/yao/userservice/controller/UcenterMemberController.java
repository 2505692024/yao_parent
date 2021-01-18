package com.yao.userservice.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.yao.commonutils.JwtUtils;
import com.yao.commonutils.Result;
import com.yao.userservice.entity.UcenterMember;
import com.yao.userservice.entity.vo.RegisterVo;
import com.yao.userservice.service.UcenterMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author yaoheng
 * @since 2021-01-04
 */
@Api(value = "登录业务", description = "登录业务")
@RestController
@RequestMapping("/userservice/ucenter-member")
@CrossOrigin
public class UcenterMemberController {
    @Resource
    private UcenterMemberService ucenterMemberService;

    @ApiOperation(value = "/login", notes = "登录接口")
    @PostMapping("/login")
    public Result login(@RequestBody UcenterMember ucenterMember) {
        String token = ucenterMemberService.login(ucenterMember);
        return Result.ok().data("token", token);
    }

    @ApiOperation(value = "/register", notes = "注册接口")
    @PostMapping("/register")
    public Result registerUser(@RequestBody RegisterVo registerVo) {
        return ucenterMemberService.register(registerVo);
    }

    @ApiOperation(value = "/getMemberInfo", notes = "根据token获取用户信息")
    @GetMapping("/getMemberInfo")
    public Result getMemberInfo(HttpServletRequest request){
        String numberIdByJwtToken = JwtUtils.getNumberIdByJwtToken(request);
        UcenterMember userInfo = ucenterMemberService.getById(numberIdByJwtToken);
        return Result.ok().data("userInfo",userInfo);
    }
}

