package com.woniuxy.realm;



import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.woniuxy.util.CustomizedToken;

public class TelRealms extends AuthorizingRealm{

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("电话登录的授权");
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("电话登录的认证");
		// 1. 把AuthenticationToken转换为CustomizedToken
        CustomizedToken customizedToken = (CustomizedToken) token;
	    String tel=(String) customizedToken.getUsername();
	    String account=tel;
		String[] split = tel.split("\\|");
		tel=split[0];
		String realcode=split[1];
		realcode = new SimpleHash("MD5",realcode,null,1024).toString();
		SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(account,realcode,getName());
		System.out.println("----------------");
		return info;
	}

}
