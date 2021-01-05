package com.yao.cmsservice.service;

import com.yao.cmsservice.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author yaoheng
 * @since 2021-01-03
 */
public interface CrmBannerService extends IService<CrmBanner> {
    /**
     * 获取所有banner
     * @return
     */
    List<CrmBanner> getAllBanner();
}
