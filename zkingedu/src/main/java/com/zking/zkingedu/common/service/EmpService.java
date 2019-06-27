package com.zking.zkingedu.common.service;

import com.zking.zkingedu.common.model.Emp;

import java.util.List;

/**
 * 后台员工接口
 */
public interface EmpService {
    //查询所有员工
    List<Emp> getemps();
    //按照条件查询查询员工
    Emp getempbyempname(String empname);

}
