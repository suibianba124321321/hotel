package com.woniuxy.configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class ShiroConfig {
	@Bean
	public CredentialsMatcher credentialsMatcher(){
		HashedCredentialsMatcher matcher=new HashedCredentialsMatcher();
		matcher.setHashAlgorithmName("MD5");
		matcher.setHashIterations(1024);
		
		return matcher;
	}

/*	@Bean
	public UserRealm realm(CredentialsMatcher matcher){
		
		UserRealm userRealm=new UserRealm();
		userRealm.setCredentialsMatcher(matcher);
		return userRealm;
	}
	@Bean
	public SecurityManager securityManager(UserRealm userRealm){
		DefaultSecurityManager securityManager=new DefaultWebSecurityManager(userRealm);
		
		
		return securityManager;
	}*/
	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
		
		ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
		bean.setSecurityManager(securityManager);
		
		bean.setLoginUrl("/html/login.html");
		
		bean.setUnauthorizedUrl("/html/error.html");
		
		Map<String, String> map=new HashMap<>();
		map.put("/index.html", "anon");
		map.put("/html/login.html", "anon");
		map.put("/druid/**", "anon");
		map.put("/user/login", "anon");
		map.put("/user/register", "anon");
		
		
		map.put("/user/logout", "logout");
		
		map.put("/**", "authc");
		
		
		bean.setFilterChainDefinitionMap(map);
		return bean;
	}
}