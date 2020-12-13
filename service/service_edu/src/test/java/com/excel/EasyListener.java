package com.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @author yaoheng
 * @date 2020/12/12 8:52
 */
public class EasyListener extends AnalysisEventListener<ExcelPojo> {

    @Override
    public void invoke(ExcelPojo excelPojo, AnalysisContext analysisContext) {
        //一行一行读取内容
        System.out.println("数据" + excelPojo);
    }
    @Override
    public void invokeHeadMap(Map<Integer , String> headMap , AnalysisContext analysisContext){
        System.out.println("表头" + headMap);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
