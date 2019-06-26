package com.zking.zkingedu.common.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 课程类别表
 */
@Data
@Component
public class CourseType implements Serializable{
    private static final long serialVersionUID = 6300730189088570355L;
    //课程类别Id
    private Integer Tid;
    //课程类别名字
    private String tName;
}
