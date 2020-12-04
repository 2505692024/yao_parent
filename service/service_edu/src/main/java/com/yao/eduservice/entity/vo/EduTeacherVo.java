package com.yao.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author yaoheng
 * @date 2020/12/3 21:10
 */
@Data
public class EduTeacherVo {
    @ApiModelProperty(value = "讲师姓名,模糊查询")
    private String name;

    @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
    private Integer level;

    @ApiModelProperty(value = "查询开始时间", example = "2020-12-03 21:08:08")
    private String begin;

    @ApiModelProperty(value = "查询结束时间", example = "2020-12-03 21:08:08")
    private String end;
}