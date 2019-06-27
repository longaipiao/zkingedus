package com.zking.zkingedu.common.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zking.zkingedu.common.model.Role;
import com.zking.zkingedu.common.model.Treedata;
import com.zking.zkingedu.common.service.MenuService;
import com.zking.zkingedu.common.service.RoleService;
import com.zking.zkingedu.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;
    /**
     * 查询所有的角色
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/roles")
    @ResponseBody
    public Map getrole(@Param("page") String page,@Param("limit") String limit){
        Page<Object> objects = PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
        Map<String,Object> rolemap = new HashMap<>();
        List<Role> getrole = roleService.getrole();
        rolemap.put("code","0");
        rolemap.put("msg","");
        rolemap.put("count",objects.getTotal());
        rolemap.put("data",getrole);
        return rolemap;
    }

    /**
     * 修改权限
     * @param menujson
     * @param roleid
     * @return
     */
    @RequestMapping("/updaterole_menu")
    @ResponseBody
    @Transactional//事物
    public String updaterole_menu(String menujson, String roleid){
        //接受前台传过来的json数据 转化为list
        if(menujson!=null&&roleid!=null){
            ArrayList<Treedata> treedataArrayList = JSON.parseObject(menujson, new TypeReference<ArrayList<Treedata>>() {});
            List<Integer> ls = new ArrayList<>();//实例化一个装菜单ID的list
            //遍历取出选中的菜单ID装入list集合中
            for(int i = 0;i < treedataArrayList.size();++i){
                Integer id = treedataArrayList.get(i).getId();
                ls.add(id);
                List<Treedata> children = treedataArrayList.get(i).getChildren();
                for (Treedata child : children) {
                    ls.add(child.getId());
                }
            }
            int rid = Integer.parseInt(roleid);
            List<Integer> getrole = menuService.getrole(rid);


//            roleService.deleterolebyid(rid);//先删除原来的
//            //后加
//            for (Integer l : ls) {
//                roleService.addrolebyid(rid,l);
//            }
        }
        return "ok";
    }


    /**
     * 修改角色名称
     * @param role
     * @return
     */
    @RequestMapping("/updaterolename")
    @ResponseBody
    public String updaterolenamebyid(Role role){
        System.out.println(role);
        if(role==null){
            return "no";
        }
        roleService.updaterolenamebyid(role.getRoleName(),role.getRoleID()+"");
        return "ok";
    }

    /**
     * 删除角色
     * @param roleid
     * @return
     */
    @RequestMapping("/delrole")
    @ResponseBody
    @Transactional//事物
    public String delrole(Integer roleid){
        if(roleid==null){
            return "no";
        }
        roleService.delrolebyid(roleid);
        return "ok";
    }

    /**
     * 添加角色
     * @param role
     * @return
     */
    @RequestMapping("/addrole")
    @ResponseBody
    @Transactional//事物
    public Integer addrole(Role role){
        int existsrolename = roleService.existsrolename(role.getRoleName());
        if (existsrolename>0){
            return 0;
        }
        int addrole = roleService.addrole(role);
        Integer roleID = role.getRoleID();
        return roleID;
    }

}
