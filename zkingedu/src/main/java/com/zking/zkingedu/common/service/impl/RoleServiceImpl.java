package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.RoleDao;
import com.zking.zkingedu.common.model.Role;
import com.zking.zkingedu.common.service.RoleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色接口服务层  实现
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleDao roleDao;

    @Override
    public List<Role> getrole() {
        return roleDao.getrole();
    }
    /**
     * 修改角色名称
     * @param
     */
    @Override
    public void updaterolenamebyid(@Param("rolename") String rolename,@Param("roleid") String roleid) {
        roleDao.updaterolenamebyid(rolename,roleid);
    }

    /**
     * 根据角色删除所有的菜单
     * @param roleid
     */
    @Override
    public void delmenurolebyid(@Param("roleid") Integer roleid,@Param("str") String str){
        roleDao.delmenurolebyid(roleid,str);
    }

    /**
     * 根据角色添加菜单
     * @param roleid
     * @param str
     */
    @Override
    public void addmenurolebyid(@Param("roleid") Integer roleid,@Param("str") String str){
        roleDao.addmenurolebyid(roleid,str);
    }
    /**
     * 删除角色
     * @param roleid
     */
    @Override
    public void delrolebyid(Integer roleid) {
        roleDao.delrolebyid(roleid);
    }
    /**
     * 添加角色
     * @param role
     * @return
     */
    @Override
    public int addrole(Role role) {
        return roleDao.addrole(role);
    }
    /**
     * 是否存在
     * @param rolename
     * @return
     */
    @Override
    public int existsrolename(String rolename) {
        return roleDao.existsrolename(rolename);
    }

}
