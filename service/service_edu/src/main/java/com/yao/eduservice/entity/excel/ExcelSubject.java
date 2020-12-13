package com.yao.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yaoheng
 * @date 2020/12/12 11:20
 */
@Data
public class ExcelSubject {

    @ApiModelProperty("一级分类")
    @ExcelProperty(index = 0)
    private String oneSubject;

    @ApiModelProperty("二级分类")
    @ExcelProperty(index = 1)
    private String twoSubject;
}
