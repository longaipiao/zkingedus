package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.Emp;
import com.zking.zkingedu.common.model.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 后台员工接口
 */
public interface EmpDao {


    //查询所有员工
    List<Emp> getemps(@Param("str") String str);
    //按照名字查询查询员工
    Emp getempbyempname(@Param("empname") String empname);
    //删除员工
    int delempbyid(@Param("empid") Integer empid);
    //增加员工
    int addemp(Emp emp);
    //修改员工
    int updateempbyid(Emp emp);
    //按照员工ID查看角色ID
    int getroleid(Integer empid);
    //修改员工的角色ID
    int updateemproleid(@Param("empid") Integer empid,@Param("roleid") Integer roleid);
    //添加t_emp_role表
    int addt_emp_role(@Param("empid") Integer empid,@Param("roleid") Integer roleid);
    //按照员工ID查看角色
    String getrole(@Param("empid") Integer empid);
}
