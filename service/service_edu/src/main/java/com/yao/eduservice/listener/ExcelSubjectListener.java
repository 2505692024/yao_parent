package com.yao.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yao.eduservice.entity.EduSubject;
import com.yao.eduservice.entity.excel.ExcelSubject;
import com.yao.eduservice.service.EduSubjectService;

/**
 * @author yaoheng
 * @date 2020/12/12 11:25
 */
public class ExcelSubjectListener extends AnalysisEventListener<ExcelSubject> {
    public EduSubjectService eduSubjectService;

    public ExcelSubjectListener() {
    }

    public ExcelSubjectListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    @Override
    public void invoke(ExcelSubject excelSubject, AnalysisContext analysisContext) {
        //读取excel内容
        if (excelSubject == null) {
            throw new RuntimeException("文件内容为空");
        }
        //一行一行的读取，每次读取都会产生两个值，并且带有层级关系
        //判断一级分类是否重复
        EduSubject eduSubject = this.existOneSubject(eduSubjectService, excelSubject.getOneSubject());
        if (eduSubject == null) {
            //添加
            eduSubject = new EduSubject();
            eduSubject.setParentId("0");
            eduSubject.setTitle(excelSubject.getOneSubject());
            eduSubjectService.save(eduSubject);

        }
        //添加二级分类
        String pid = eduSubject.getId();
        EduSubject eduSubject1 = this.existTwoSubject(eduSubjectService, excelSubject.getTwoSubject(), pid);
        if (eduSubject1 == null) {
            eduSubject1 = new EduSubject();
            eduSubject1.setTitle(excelSubject.getTwoSubject());
            eduSubject1.setParentId(pid);
            eduSubjectService.save(eduSubject1);
        }

    }

    /**
     * 判断一级菜单不能重复
     *
     * @param eduSubjectService
     * @param name
     * @return
     */
    private EduSubject existOneSubject(EduSubjectService eduSubjectService, String name) {

        return eduSubjectService.getOne(new QueryWrapper<EduSubject>().eq("title", name).eq("parent_id", "0"));
    }

    /**
     * 判断二级菜单不能重复
     *
     * @param name
     */
    private EduSubject existTwoSubject(EduSubjectService eduSubjectService, String name, String pid) {
        return eduSubjectService.getOne(new QueryWrapper<EduSubject>().eq("title", name).eq("parent_id", pid));
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
