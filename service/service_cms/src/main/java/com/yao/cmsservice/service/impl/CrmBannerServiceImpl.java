package com.yao.cmsservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yao.cmsservice.entity.CrmBanner;
import com.yao.cmsservice.mapper.CrmBannerMapper;
import com.yao.cmsservice.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author yaoheng
 * @since 2021-01-03
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {
    @Resource
    private CrmBannerMapper crmBannerMapper;

    @Override
    public List<CrmBanner> getAllBanner() {
        return crmBannerMapper.selectList(new QueryWrapper<CrmBanner>().orderByDesc("id").last("limit 4"));
    }
}
