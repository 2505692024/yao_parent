package com.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yaoheng
 * @date 2020/12/11 9:25
 */
public class TestEasyExcel {
    public static void main(String[] args) {
        //设置写入文件的地址和名称
        String fileName = "E:\\write.xlsx";
//        EasyExcel.write(fileName, ExcelPojo.class).sheet("学生列表").doWrite(getList());
        //测试读操作
        EasyExcel.read(fileName,ExcelPojo.class,new EasyListener()).sheet().doRead();
    }

    /**
     * 创建返回list的方法
     *
     * @return
     */
    private static List<ExcelPojo> getList() {
        List<ExcelPojo> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ExcelPojo excelPojo = new ExcelPojo();
            excelPojo.setSno(i);
            excelPojo.setSname("小明"+i);
            list.add(excelPojo);
        }
        return list;
    }
}
