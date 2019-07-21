package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.EmpDao;
import com.zking.zkingedu.common.model.Emp;
import com.zking.zkingedu.common.model.Menu;
import com.zking.zkingedu.common.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 后台员工接口  实现层
 */
@Service("empService")
public class EmpServiceImpl implements EmpService {

    @Resource
    private EmpDao empDao;
    //查询所有员工
    @Override
    public List<Map> getemps(String str) {
        return empDao.getemps(str);
    }
    //按照用户名查询查询员工
    @Override
    public Emp getempbyempname(String empname){
        Emp getempbyempname = empDao.getempbyempname(empname);
        return getempbyempname;
    }

    /**
     * 删除员工
     * @param empid
     * @return
     */
    @Override
    public int delempbyid(Integer empid) {
        return empDao.delempbyid(empid);
    }
    //增加员工
    @Override
    public int addemp(Emp emp) {
        return empDao.addemp(emp);
    }
    //修改员工
    @Override
    public int updateempbyid(Emp emp) {
        return empDao.updateempbyid(emp);
    }
    //按照员工ID查看角色ID
    @Override
    public int getroleid(Integer empid) {
        return empDao.getroleid(empid);
    }
    //修改员工的角色ID
    @Override
    public int updateemproleid(Integer empid, Integer roleid) {
        return empDao.updateemproleid(empid,roleid);
    }
    //添加t_emp_role表
    @Override
    public int addt_emp_role(Integer empid, Integer roleid) {
        return empDao.addt_emp_role(empid,roleid);
    }
    //按照员工ID查看角色
    @Override
    public String getrole(Integer empid) {
        return empDao.getrole(empid);
    }


    ;
}
