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
    //根据用户ID查询该用户该用户所有的菜单
    @Override
    public List<Menu> getmenus(Integer empid) {
        List<Menu> results = new ArrayList();
        List<Menu> menus = empDao.getmenus(empid);
        if(menus!=null&&menus.size()!=0){
            for (int i = 0; i < menus.size(); ++i) {
                //如果菜单父ID==0
                if(((Menu)menus.get(i)).getMenuFid()==0){
                    Menu m1 = new Menu();
                    m1.setMenuID(((Menu)menus.get(i)).getMenuID());
                    m1.setMenuName(((Menu)menus.get(i)).getMenuName());
                    m1.setMenuFid(((Menu)menus.get(i)).getMenuFid());
                    m1.setMenuImg(((Menu)menus.get(i)).getMenuImg());
                    m1.setMenuCode(((Menu)menus.get(i)).getMenuCode());
                    m1.setMenuURL(((Menu)menus.get(i)).getMenuURL());
                    m1.setMenuRank(((Menu)menus.get(i)).getMenuRank());
                    m1.setMenuSort(((Menu)menus.get(i)).getMenuSort());
                    List<Menu> menu1 = new ArrayList();
                    for (int j = 0; j < menus.size(); ++j) {
                        //所有菜单的父ID等于顶级菜单的ID
                        if(((Menu)menus.get(i)).getMenuID()==((Menu)menus.get(j)).getMenuFid()){
                            Menu m2 = new Menu();
                            m2.setMenuID(((Menu)menus.get(j)).getMenuID());
                            m2.setMenuName(((Menu)menus.get(j)).getMenuName());
                            m2.setMenuFid(((Menu)menus.get(j)).getMenuFid());
                            m2.setMenuImg(((Menu)menus.get(j)).getMenuImg());
                            m2.setMenuCode(((Menu)menus.get(j)).getMenuCode());
                            m2.setMenuURL(((Menu)menus.get(j)).getMenuURL());
                            m2.setMenuRank(((Menu)menus.get(j)).getMenuRank());
                            m2.setMenuSort(((Menu)menus.get(j)).getMenuSort());
                            menu1.add(m2);
                        }
                    }
                    m1.setMenus(menu1);
                    results.add(m1);
                }
            }
        }
        return results;
    }

    ;
}
