package com.yao.cmsservice.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.yao.cmsservice.entity.CrmBanner;
import com.yao.cmsservice.service.CrmBannerService;
import com.yao.commonutils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author yaoheng
 * @since 2021-01-03
 */
@Api(value = "banner资源管理", description = "banner资源管理")
@RestController
@RequestMapping("/cmsservice/crm-banner")
@CrossOrigin
public class CrmBannerController {
    @Resource
    private CrmBannerService crmBannerService;

    @Cacheable(value = "banner",key = "'selectList'")
    @ApiOperation(value = "/getAllBanner", notes = "查询所有幻灯片")
    @GetMapping("/getAllBanner")
    public Result getAllBanner() {
        List<CrmBanner> crmBanners = crmBannerService.getAllBanner();
        return Result.ok().data("data", crmBanners);
    }
}

