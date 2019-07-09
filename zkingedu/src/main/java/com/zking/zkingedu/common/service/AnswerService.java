package com.zking.zkingedu.common.service;

import com.zking.zkingedu.common.model.Answer;
import com.zking.zkingedu.common.model.Title;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 答案接口
 */
public interface AnswerService {

    //根据题目id查看所有的题目
    List<Answer> getAnswerAll(@Param("tid") Integer tid);

    //批量增加答案
    Integer addAnswer(@Param("ls") List<Answer> ls);
    //根据阶段id删除答案
    Integer deletedaan(@Param("tid") Integer tid);
    //修改答案A
    Integer uppdatedaana(Answer answer);
    //修改答案B
    Integer uppdatedaanb(Answer answer);
    //修改答案C
    Integer uppdatedaanc(Answer answer);
    //修改答案D
    Integer uppdatedaand(Answer answer);


    /**
     * 题目接口
     */
    //查询所有的题目条件
    List<Title> getAll(Title title);
    //查询所有
    List<Title> getAlls(@Param("tid") Integer tid);

    //增加题目
    Integer addTitle(Title title);
    //修改题目状态
    Integer updatekq(@Param("tid") Integer tid);
    //修改题目状态
    Integer updategb(@Param("tid") Integer tid);
    //根据id删除题目
    Integer deletetim(@Param("tid") Integer tid);
    //根据tid修改
    Integer updatetim(Title title);
}
