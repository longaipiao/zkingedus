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

/**
 * 后台员工接口  实现层
 */
@Service("empService")
public class EmpServiceImpl implements EmpService {

    @Resource
    private EmpDao empDao;
    //查询所有员工
    @Override
    public List<Emp> getemps() {
        return empDao.getemps();
    }
    //按照用户名查询查询员工
    @Override
    public Emp getempbyempname(String empname){
        return empDao.getempbyempname(empname);
    }


    ;
}
