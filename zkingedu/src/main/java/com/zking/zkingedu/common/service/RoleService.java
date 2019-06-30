package com.zking.zkingedu.common.service;

import com.zking.zkingedu.common.model.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色接口
 */
public interface RoleService {
    /**
     * 查询所有的角色
     * @return
     */
    List<Role> getrole();

    /**
     * 修改角色名称
     * @param
     */
    void updaterolenamebyid(@Param("rolename") String rolename,@Param("roleid") String roleid);

    /**
     * 根据角色删除所有的菜单
     * @param roleid
     */
    void delmenurolebyid(@Param("roleid") Integer roleid,@Param("str") String str);

    /**
     * 根据角色添加菜单
     * @param roleid
     * @param str
     */
    void addmenurolebyid(@Param("roleid") Integer roleid,@Param("str") String str);

    /**
     * 删除角色角色
     * @param roleid
     */
    void delrolebyid(@Param("roleid") Integer roleid);

    /**
     * 添加角色
     * @param role
     * @return
     */
    int addrole(Role role);

    /**
     * 是否存在
     * @param rolename
     * @return
     */
    int existsrolename(@Param("rolename") String rolename);
}
