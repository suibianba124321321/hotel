package com.woniuxy.util;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;


public class CustomizedModularRealmAuthenticator extends ModularRealmAuthenticator{

	
	   @Override
	    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken)
	            throws AuthenticationException {
	        // 判断getRealms()是否返回为空
	        assertRealmsConfigured();
	        // 强制转换回自定义的CustomizedToken
	        CustomizedToken customizedToken = (CustomizedToken) authenticationToken;
	        // 登录类型
	        String loginType = customizedToken.getLoginType();
	        
	        System.out.println(loginType);
	        
	        System.out.println("验证当前的realms的类型");
	        // 所有Realm
	        Collection<Realm> realms = getRealms();
	        // 登录类型对应的所有Realm
	        Collection<Realm> typeRealms = new ArrayList<>();
	        
	        for (Realm realm : realms) {
	        	System.out.println("所有的realms"+realm.getName()+"------"+loginType);
	            if (realm.getName().contains(loginType)){
	            	System.out.println("--------00000000000----------00000000-");
	            	System.out.println(realm.getName()+"当前realm");
	                typeRealms.add(realm);}
	        }

	        // 判断是单Realm还是多Realm
	        if (typeRealms.size() == 1)
	            return doSingleRealmAuthentication(typeRealms.iterator().next(), customizedToken);
	        else
	            return doMultiRealmAuthentication(typeRealms, customizedToken);
	    }

	
	
}
