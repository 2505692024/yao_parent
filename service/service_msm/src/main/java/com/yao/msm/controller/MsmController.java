package com.yao.msm.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.yao.commonutils.Result;
import com.yao.msm.service.MsmService;
import com.yao.msm.utils.RandomUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author yaoheng
 * @date 2021/1/4 14:53
 */
@Api(value = "阿里云短信服务", description = "阿里云短信服务")
@RestController
@RequestMapping("/edumsm/msm")
@CrossOrigin
public class MsmController {
    @Resource
    private MsmService msmService;
    @Resource
    private RedisTemplate redisTemplate;

    @ApiOperation(value = "/send", notes = "发送验证码")
    @GetMapping("/send/{phone}")
    public Result send(@PathVariable String phone) {
        String code = (String) redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)) {
            return Result.ok();
        }
        code = RandomUtil.getFourBitRandom();
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        boolean send = msmService.send(map, phone);
        if (send) {
            //发送成功后将值放入到redis中并设置有效时间
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return Result.ok().message("发送成功");
        } else {
            return Result.error().message("发送失败");
        }
    }
}
