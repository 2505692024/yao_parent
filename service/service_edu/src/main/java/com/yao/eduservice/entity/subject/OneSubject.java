package com.yao.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yaoheng
 * @date 2020/12/12 17:14
 */
@Data
public class OneSubject {
    private String id;
    private String title;
    //一个一级分类可能包含多个二级分类
    private List<TwoSubject> children = new ArrayList<>();
}
