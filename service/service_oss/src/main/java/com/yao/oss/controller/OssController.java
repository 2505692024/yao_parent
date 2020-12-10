package com.yao.oss.controller;

import com.yao.commonutils.Result;
import com.yao.oss.service.OssService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author yaoheng
 * @date 2020/12/9 10:36
 */
@RestController
@RequestMapping("/eduoss/ossFile")
@CrossOrigin
public class OssController {
    @Resource
    private OssService ossService;
    @ApiOperation(value = "上传头像")
    @PostMapping
    public Result uploadOssFile(MultipartFile file){
        //上传文件方法
        String url = ossService.uploadOssAvatar(file);
        return Result.ok().data("url", url);
    }
}
