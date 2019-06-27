package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.MenuDao;
import com.zking.zkingedu.common.model.Menu;
import com.zking.zkingedu.common.model.Treedata;
import com.zking.zkingedu.common.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单接口  实现层
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {
    @Resource
    private MenuDao menuDao;
    //根据用户ID查询该用户该用户所有的菜单
    @Override
    public List<Menu> getmenus(Integer empid) {
        List<Menu> results = new ArrayList();
        List<Menu> menus = menuDao.getmenus(empid);
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
    //查询出所有的树菜单
    @Override
    public List<Treedata> getdata(Integer empid) {
        List<Treedata> results = new ArrayList();//装备树数据源的集合
        List<Menu> menus = menuDao.getmenus(empid);//查询出所有的菜单集合
        if(menus!=null&&menus.size()!=0){//判断有数据
            for (int i = 0; i < menus.size(); ++i) {//遍历菜单集合
                if(((Menu)menus.get(i)).getMenuFid()==0){//如果菜单父ID==0 证明是最顶级的
                    Treedata treedata = new Treedata();//实例化一个树对象
                    treedata.setId(((Menu)menus.get(i)).getMenuID());//把菜单ID赋值给树ID
                    treedata.setTitle(((Menu)menus.get(i)).getMenuName());//把菜单名字赋值给树NAME
                    List<Treedata> treelist = new ArrayList();//创建一个树集合
                    for (int j = 0; j < menus.size(); ++j) {//再次遍历菜单集合
                        if(((Menu)menus.get(i)).getMenuID()==((Menu)menus.get(j)).getMenuFid()) {//所有菜单的父ID等于顶级菜单的ID
                            Treedata treedata1 = new Treedata();//再实例化一个树对象
                            treedata1.setId(((Menu)menus.get(j)).getMenuID());//把菜单ID赋值给树ID
                            treedata1.setTitle(((Menu)menus.get(j)).getMenuName());//把菜单名字赋值给树NAME
                            treelist.add(treedata1);//把第二个树对象赋值给树集合
                        }
                    }
                    treedata.setChildren(treelist);//把树集合放入树对象
                    results.add(treedata);//把一个个树对象放入树数据源的集合
                }
            }
        }
        return results;
    }
    //根据不同的角色返回相对应的菜单ID
    @Override
    public List<Integer> getrole(Integer roleid) {
        return menuDao.getrole(roleid);
    }


}
