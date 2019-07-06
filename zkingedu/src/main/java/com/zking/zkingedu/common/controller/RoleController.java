package com.zking.zkingedu.common.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zking.zkingedu.common.model.Role;
import com.zking.zkingedu.common.model.Treedata;
import com.zking.zkingedu.common.service.EmpService;
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

    @RequestMapping("/getrole")
    @ResponseBody
    public List<Role> getrole(){
        List<Role> getrole = roleService.getrole();
        return getrole;
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
            List<Integer> lsa = new ArrayList<>();
            //遍历取出选中的菜单ID装入list集合中
            for(int i = 0;i < treedataArrayList.size();++i){
                Integer id = treedataArrayList.get(i).getId();
                ls.add(id);
                lsa.add(id);
                List<Treedata> children = treedataArrayList.get(i).getChildren();
                for (Treedata child : children) {
                    ls.add(child.getId());
                    lsa.add(child.getId());
                }
            }
            int rid = Integer.parseInt(roleid);
            List<Integer> getrole = menuService.getrole(rid);//根据角色ID查看该权限的菜单id

            //查找出应该添加的菜单
            for(int j = 0;j < getrole.size();j++){//遍历他原有的菜单id
                for (int z = 0; z < ls.size();z++){//遍历修改后的菜单ID
                    if(getrole.get(j)==ls.get(z)){//如果里面有相同的ID
                        ls.remove(z);//移除ls集合里相同的
                    }
                }
            }
            //查找出应该删除的
            for(int j = 0;j < lsa.size();j++){//遍历他原有的菜单id
                for (int z = 0; z < getrole.size();z++){//遍历修改后的菜单ID
                    if(lsa.get(j)==getrole.get(z)){//如果里面有相同的ID
                        getrole.remove(z);//移除ls集合里相同的
                    }
                }
            }
            //再遍历删除后的两个集合   添加ls集合的  删除getrole集合的
            if(getrole!=null&getrole.size()!=0){
                StringBuffer str = new StringBuffer();
                for (Integer integer : getrole) {//应该删除的
                    str.append(integer+",");
                }
                String string = str.substring(0, str.lastIndexOf(","));
                roleService.delmenurolebyid(rid,string);//批量删除
            }

           if(ls!=null&ls.size()!=0){
               StringBuffer str = new StringBuffer();
               for (Integer l : ls) {//应该添加的
                   str.append("("+rid+","+l+"),");
               }
               String string = str.substring(0, str.lastIndexOf(","));
               roleService.addmenurolebyid(rid,string);//批量添加
           }
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
