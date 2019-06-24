package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.Emp;
import com.zking.zkingedu.common.model.Menu;

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
    Emp getempbyempname(String empname);
    //根据用户ID查询该用户该用户所有的菜单
    List<Menu> getmenus(Integer empid);
}
