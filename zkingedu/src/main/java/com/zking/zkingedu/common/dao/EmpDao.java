package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.Emp;
import com.zking.zkingedu.common.model.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 后台员工接口
 */
public interface EmpDao {

    /**
     * 测试二级缓存
     * @return
     */
    //查询所有员工
    List<Emp> getemps();
    //按照名字查询查询员工
    Emp getempbyempname(@Param("empname") String empname);
    //删除员工
    int delempbyid(@Param("empid") Integer empid);
    //增加员工
    int addemp(Emp emp);
    //修改员工
    int updateempbyid(Emp emp);
}
