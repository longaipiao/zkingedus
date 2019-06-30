package com.zking.zkingedu.common.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 题目表
 * @ClassName Title
 * @Author likai
 **/
@Data
@Component
public class Title implements Serializable {
    private static final long serialVersionUID = -3523542992767880892L;
    //题目ID
    private Integer titleID;
    //题目类别ID
    private Integer titleCid;
    //题目内容
    private String titleContent;
    //题目描述
    private String titleDescribe;
    //发布时间
    private String titleTime;
    //状态
    private Integer titleState;
}
