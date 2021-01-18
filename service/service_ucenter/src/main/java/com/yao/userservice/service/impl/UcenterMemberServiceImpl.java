package com.yao.userservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yao.commonutils.JwtUtils;
import com.yao.commonutils.MD5;
import com.yao.commonutils.Result;
import com.yao.userservice.entity.UcenterMember;
import com.yao.userservice.entity.vo.RegisterVo;
import com.yao.userservice.mapper.UcenterMemberMapper;
import com.yao.userservice.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author yaoheng
 * @since 2021-01-04
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Resource
    private UcenterMemberMapper ucenterMemberMapper;
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public String login(UcenterMember ucenterMember) {
        if (StringUtils.isEmpty(ucenterMember.getMobile()) || StringUtils.isEmpty(ucenterMember.getPassword())) {
            throw new RuntimeException("登陆失败");
        }
        UcenterMember ucenter = ucenterMemberMapper.selectOne(new QueryWrapper<UcenterMember>().eq("mobile", ucenterMember.getMobile()));
        if (ucenter == null) {
            throw new RuntimeException("登陆失败");
        }
        //加密后的密码进行比较
        if (!MD5.encrypt(ucenterMember.getPassword()).equals(ucenter.getPassword())) {
            throw new RuntimeException("登陆失败");
        }
        if (ucenter.getIsDisabled()) {
            throw new RuntimeException("登陆失败");
        }
        //登陆成功，返回token
        return JwtUtils.getJwtToken(ucenter.getId(), ucenter.getNickname());
    }

    @Override
    public Result register(RegisterVo registerVo) {
        if (StringUtils.isEmpty(registerVo.getCode()) || StringUtils.isEmpty(registerVo.getMobile()) || StringUtils.isEmpty(registerVo.getNickname()) || StringUtils.isEmpty(registerVo.getPassword())) {
            throw new RuntimeException("注册失败");
        }
        //获取redis中的验证码
        String redisCode = (String) redisTemplate.opsForValue().get(registerVo.getMobile());
        if (!redisCode.equals(registerVo.getCode())) {
            return Result.error().message("验证码不正确");
        }
        Integer count = ucenterMemberMapper.selectCount(new QueryWrapper<UcenterMember>().eq("mobile", registerVo.getMobile()));
        if (count > 0) {
            return Result.error().message("该手机号已存在");
        }
        UcenterMember u = new UcenterMember();
        u.setMobile(registerVo.getMobile());
        u.setPassword(MD5.encrypt(registerVo.getPassword()));
        u.setIsDisabled(false);
        u.setNickname(registerVo.getNickname());
        u.setAvatar("https://yao-parent.oss-cn-beijing.aliyuncs.com/2020-12-30/159624ca12e14a2ea10dbb389bf19f7ffile.png");
        int insert = ucenterMemberMapper.insert(u);
        if (insert > 0) {
            return Result.ok().message("注册成功");
        } else {
            return Result.error().message("注册失败");
        }
    }

    @Override
    public UcenterMember getMemberByOpenId(String openid) {
        UcenterMember ucenterMember = ucenterMemberMapper.selectOne(new QueryWrapper<UcenterMember>().eq("openid", openid));
        return ucenterMember;
    }
}
