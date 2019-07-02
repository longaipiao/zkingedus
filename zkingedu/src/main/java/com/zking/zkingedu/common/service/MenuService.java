package com.zking.zkingedu.common.service;

import com.zking.zkingedu.common.model.Menu;
import com.zking.zkingedu.common.model.Treedata;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单接口
 */
public interface MenuService {
    //根据用户ID查询该用户该用户所有的菜单
    List<Menu> getmenus(Integer empid);
    //查询所有的树菜单
    List<Treedata> getdata(Integer empid);

    //查询该角色的所有权限
    List<Integer> getrole(@Param(value="roleid")Integer roleid);

}
