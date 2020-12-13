package com.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author yaoheng
 * @date 2020/12/11 9:15
 */
@Data
public class ExcelPojo {
    /**
     * 设置表头
     */
    @ExcelProperty(value = "学生编号",index = 0)
    private Integer sno;
    @ExcelProperty(value = "学生姓名",index = 1)
    private String sname;
}
