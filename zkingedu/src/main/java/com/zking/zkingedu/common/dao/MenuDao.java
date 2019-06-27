package com.zking.zkingedu.common.dao;

import com.zking.zkingedu.common.model.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单接口
 */
public interface MenuDao {
    //根据用户ID查询该用户该用户所有的菜单
    List<Menu> getmenus(@Param(value="empid")Integer empid);

    //查询该角色的所有权限
    List<Integer> getrole(@Param(value="roleid")Integer roleid);



}
