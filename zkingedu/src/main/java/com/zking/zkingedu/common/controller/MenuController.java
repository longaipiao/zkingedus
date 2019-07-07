package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.model.Menu;
import com.zking.zkingedu.common.model.Treedata;
import com.zking.zkingedu.common.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Controller
@Slf4j
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    //个人权限菜单展示
    @ResponseBody
    @RequestMapping(value = "/getmenus")
    public List<Menu> getmuen(Integer empid){
        List<Menu> menus = menuService.getmenus(empid);
        return menus;
    }

    //树菜单展示
    @ResponseBody
    @RequestMapping(value = "/trees")
    public List<Treedata> gettrees(Integer roleid){//返回树结构的list
        List<Treedata> treedata = menuService.getdata(null);//查询出一个装有树结构的集合根据
        List<Integer> getrole = menuService.getrole(roleid);//查询出根据员工ID 查询出所有的菜单ID  返回的只有ID
        if(treedata!=null&&treedata.size()!=0){
            for (int i = 0; i < treedata.size(); ++i){
                List<Treedata> children = treedata.get(i).getChildren();
                for (int j = 0; j < children.size(); ++j){
                    for (int z = 0; z < getrole.size(); ++z) {
                        if(children.get(j).getId()==getrole.get(z)){
                            children.get(j).setChecked(true);
                        }
                    }
                }
            }
        }
            return treedata;
    }

    @ResponseBody
    @RequestMapping(value = "/")
    public List<Integer> getrole(Integer roleid){
        List<Integer> getrole = menuService.getrole(roleid);
        return getrole;
    }

}
