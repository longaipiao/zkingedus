package com.zking.zkingedu.common.shiro;


import com.zking.zkingedu.common.model.Emp;
import com.zking.zkingedu.common.service.EmpService;
import com.zking.zkingedu.common.model.User;
import com.zking.zkingedu.common.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private EmpService empService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("进来验证了");
        //获得验证主体（subject）的账号和密码
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //根据主体账号，从数据库表中获得该账号对象
        Emp dbEmp = empService.getempbyempname(token.getUsername());
        System.out.println(dbEmp);
        //如果账号对象为null，表示该数据库表中没有该账户
        if (dbEmp == null) {
            return null;
        }
        //最后的比对需要交给安全管理器（Su）
        //三个参数进行初步的简单认证信息对象的包装
        AuthenticationInfo info = new SimpleAuthenticationInfo(dbEmp, dbEmp.getEmpPassword(), this.getClass().getSimpleName());

        return info;
    }
}