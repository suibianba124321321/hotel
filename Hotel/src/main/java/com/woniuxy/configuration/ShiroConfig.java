package com.woniuxy.configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.woniuxy.realm.UserRealm;



@Configuration
public class ShiroConfig {
	@Bean
	public CredentialsMatcher matcher() {
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
		matcher.setHashAlgorithmName("MD5");
		matcher.setHashIterations(1024);
		return matcher;
	}
	
	//realm
	@Bean
	public UserRealm realm(CredentialsMatcher matcher){
		System.out.println("创建realm");
		UserRealm userRealm = new UserRealm();
		System.out.println(userRealm);
		//设置加密类型及次数
		userRealm.setCredentialsMatcher(matcher);
		
		return userRealm;
	}
	//securityManager
	@Bean
	public SecurityManager securityManager(UserRealm userRealm) {
		System.out.println("创建securityManager");
		//System.out.println(userRealm);
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		manager.setRealm(userRealm);
		return manager;
	}
	//shiro过滤器
	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
		
		ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
		bean.setSecurityManager(securityManager);
		//登录界面
		bean.setLoginUrl("/login.html");
		//无权限界面
		bean.setUnauthorizedUrl("/index.html");
		
		Map<String, String> map=new HashMap<>();
		map.put("/index.html", "anon");
		map.put("/login.html", "anon");
		map.put("/druid/**", "anon");
		map.put("/user/login", "anon");
		map.put("/user/register", "anon");
		map.put("/**", "anon");
	
		map.put("/logout", "logout");
		
		bean.setFilterChainDefinitionMap(map);
		return bean;
	}
}
