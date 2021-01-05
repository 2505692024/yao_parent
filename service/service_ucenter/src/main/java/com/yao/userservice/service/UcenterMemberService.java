package com.yao.userservice.service;

import com.yao.commonutils.Result;
import com.yao.userservice.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yao.userservice.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author yaoheng
 * @since 2021-01-04
 */
public interface UcenterMemberService extends IService<UcenterMember> {
    /**
     * 登录接口
     * @param ucenterMember
     * @return
     */
    String login(UcenterMember ucenterMember);

    /**
     * 注册接口
     * @param registerVo
     */
    Result register(RegisterVo registerVo);
}
