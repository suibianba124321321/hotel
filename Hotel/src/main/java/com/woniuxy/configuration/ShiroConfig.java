package com.woniuxy.configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.woniuxy.realms.AdmineRealms;



@Configuration
public class ShiroConfig {
	/**
	 * 加密配置
	 * @return
	 */
	@Bean
	public CredentialsMatcher credentialsMatcher(){
		HashedCredentialsMatcher matcher=new HashedCredentialsMatcher();
		matcher.setHashAlgorithmName("MD5");
		matcher.setHashIterations(1024);
		
		return matcher;
	}

/*@Bean
	public UserRealm realm(CredentialsMatcher matcher){
		
		UserRealm userRealm=new UserRealm();
		userRealm.setCredentialsMatcher(matcher);
		return userRealm;
	}*/
	
	@Bean
	public AdmineRealms admineRealms(CredentialsMatcher matcher){
		
		AdmineRealms admineRealms=new AdmineRealms();
		admineRealms.setCredentialsMatcher(matcher);
		return admineRealms;
	}
	
	
	/**
	 * 安全管理器
	 * @return
	 */
	@Bean
	public SecurityManager securityManager(AdmineRealms admineRealms){
		DefaultSecurityManager securityManager=new DefaultWebSecurityManager();
		securityManager.setRealm(admineRealms);
		
		return securityManager;
	}
	
	/**
	 * 工厂设置对应的过滤条件
	 * @param securityManager
	 * @return
	 */
	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
		
		ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
		bean.setSecurityManager(securityManager);
		
		bean.setLoginUrl("/backstage/login.html");
		
		bean.setUnauthorizedUrl("/backstage/error.html");
		
		Map<String, String> map=new HashMap<>();
        //后台页面登录+资源
		map.put("/js/**", "anon");
		map.put("/lib/**", "anon");
		map.put("/manager/login", "anon");
		map.put("/backstage/css/**", "anon");
		map.put("/backstage/error.html", "anon");
		map.put("/backstage/images/**", "anon");
		map.put("/manager/logout", "logout");
		map.put("/manager/updateall", "roles[Superuser]");
		map.put("/manager/delete", "roles[Superuser]");
		map.put("/manager/add", "roles[Superuser]");
		
		
		
		map.put("/**", "authc");
		
		
		bean.setFilterChainDefinitionMap(map);
		return bean;
	}
}
