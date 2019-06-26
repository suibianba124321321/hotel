package com.woniuxy.realm;
import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import org.springframework.stereotype.Controller;

import com.woniuxy.pojo.Manager;
import com.woniuxy.pojo.Role;
import com.woniuxy.service.ManagerService;
import com.woniuxy.service.RoleService;
import com.woniuxy.util.CustomizedToken;

@Controller
public class AdmineRealms extends AuthorizingRealm{
	@Resource
	private ManagerService managerService;
	@Resource
	private RoleService roleService;
	 /**
	  * 授权
	  */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//获取用户名
		System.out.println("授权");
		Object account = principals.getPrimaryPrincipal();
		System.out.println(account);
		SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
		Manager manager = new Manager();
		manager.setAccount((String)account);
		Manager one = managerService.findOneByAccount(manager);
		Integer role_id = one.getRole_id();
		//根据角色ID查询权限
		Role role = roleService.findRoleeById(role_id);
		System.out.println("正在授权，当前角色"+role.getRole());
		info.addRole(role.getRole());

		return info;
	}

	 /**
	  * 认证
	  */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		
		
		
		System.out.println("黄锐进入认证方法");
		
        // 1. 把AuthenticationToken转换为CustomizedToken
        CustomizedToken customizedToken = (CustomizedToken) token;
        // 2. 从CustomizedToken中获取username
        String username = customizedToken.getUsername();
        System.out.println(username);
        // 3. 若用户不存在，抛出UnknownAccountException异常

        Manager old_Manager = new Manager();
        System.out.println("22222222222222222222222");
        old_Manager.setAccount(username);
        System.out.println("111111111111111111111");
        Manager sqlManager = managerService.findOneByAccount(old_Manager);
        System.out.println(username+"前端用户名");
        System.out.println(sqlManager.getPwd()+"后端密码");
        if (sqlManager == null){
            return null;
            }

        
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(sqlManager.getAccount(), sqlManager.getPwd(), null,
                getName());

		
		
		return info;
	}

}
