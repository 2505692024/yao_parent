package com.yao.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yaoheng
 * @date 2020/12/24 9:56
 */
@Data
public class EduPulishVo {
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "课程标题")
    private String title;
    @ApiModelProperty(value = "课程封面图片路径")
    private String cover;
    @ApiModelProperty(value = "总课时")
    private Integer lessonNum;
    @ApiModelProperty(value = "一级分类")
    private String subjectLevelOne;
    @ApiModelProperty(value = "二级分类")
    private String subjectLevelTwo;
    @ApiModelProperty(value = "教师名称")
    private String teacherName;
    @ApiModelProperty(value = "价格")
    private String price;
    @ApiModelProperty(value = "课程简介")
    private String description;
}
