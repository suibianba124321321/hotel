package com.woniuxy.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.woniuxy.pojo.Login;
import com.woniuxy.service.UserService;
import com.woniuxy.util.CustomizedToken;

public class UserRealm extends AuthorizingRealm{
    @Autowired
	private UserService userService;
	 //授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
     System.out.println("正在授权");
		return null;
	}
	//认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		 System.out.println("正在认证");
		 //获取账号
		 
		// 1. 把AuthenticationToken转换为CustomizedToken
	        CustomizedToken customizedToken = (CustomizedToken) token;
		 
		 
		 String account=(String) customizedToken.getUsername();
		 System.out.println("获取用户名");
		 
		 Login login=new Login();
		 login.setAccount(account);
		 //查询
		 String pwd = userService.checkPwdByaccount(login);
		 login.setPwd(pwd);
		 if(pwd==null){return null;}
		 SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(login.getAccount(),login.getPwd(),getName());
		return info;
	}

}
