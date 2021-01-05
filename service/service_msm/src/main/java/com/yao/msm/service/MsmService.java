package com.yao.msm.service;

import java.util.Map;

/**
 * @author yaoheng
 * @date 2021/1/4 14:55
 */
public interface MsmService {
    /**
     * 发送短信验证码
     * @param map
     * @param phone
     */
    boolean send(Map<String, Object> map, String phone);
}
