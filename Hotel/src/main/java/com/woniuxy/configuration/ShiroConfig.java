package com.woniuxy.configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.woniuxy.realm.AdmineRealms;
import com.woniuxy.realm.UserRealm;
import com.woniuxy.util.CustomizedModularRealmAuthenticator;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;



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
		System.out.println("创建admine");
		AdmineRealms admineRealms=new AdmineRealms();
		admineRealms.setCredentialsMatcher(matcher);
		return admineRealms;
	}
	
	@Bean
	public ShiroDialect shiroDialect(){
		return new ShiroDialect();
	}
	
	
	//realm
	@Bean
	public UserRealm realm(CredentialsMatcher matcher){
		System.out.println("创建userrealm");
		UserRealm userRealm = new UserRealm();
		System.out.println(userRealm);
		//设置加密类型及次数
		userRealm.setCredentialsMatcher(matcher);
		
		return userRealm;
	}
	
	
	@Bean 
	public AtLeastOneSuccessfulStrategy atl(){
		System.out.println("创建AtLeastOneSuccessfulStrategy");
		AtLeastOneSuccessfulStrategy atlo=new AtLeastOneSuccessfulStrategy();
		return atlo;
	}
	
	//配置自定义认证器
			@Bean 
			public CustomizedModularRealmAuthenticator authenticator(AtLeastOneSuccessfulStrategy at){
				System.out.println("配置自定义认证器");
				CustomizedModularRealmAuthenticator cmra=new CustomizedModularRealmAuthenticator();
				//配置认证策略
				cmra.setAuthenticationStrategy(at);
				return cmra;
			}
	
	

	
	
	/**
	 * 安全管理器
	 * @return
	 */
	@Bean
	public SecurityManager securityManager(AdmineRealms admineRealms,UserRealm userRealm,CustomizedModularRealmAuthenticator cmra){
		DefaultSecurityManager securityManager=new DefaultWebSecurityManager();
		securityManager.setAuthenticator(cmra);
		
		Collection<Realm> realms=new ArrayList<>();
		realms.add(userRealm);
		realms.add(admineRealms);
		securityManager.setRealms(realms);
	
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
	/*	bean.setLoginUrl("/backstage/login.html");
		
		bean.setUnauthorizedUrl("/backstage/error.html");*/
		
		
		bean.setLoginUrl("/login.html");
		//无权限界面
		bean.setUnauthorizedUrl("/backstage/error.html");
		
		Map<String, String> map=new LinkedHashMap<>();
        //后台页面登录+资源
		map.put("/backstage/login.html", "anon");
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
		map.put("/register", "anon");
		map.put("/register.html", "anon");
		map.put("/login_files/**", "anon");
		map.put("/index_files/**", "anon");
		map.put("/register_files/**", "anon");
		map.put("/index.html", "anon");

		map.put("/login.html", "anon");
		map.put("/", "anon");
		map.put("/html/login.html", "anon");
		map.put("/html/bookpage.html", "anon");
		map.put("/html/bookdetailpage.html", "anon");
		map.put("/type/text", "anon");
		map.put("/css/**", "anon");
		map.put("/js/**", "anon");
		map.put("/img/**", "anon");
		map.put("/type/show", "anon");
		
		
		

		map.put("/druid/**", "anon");
		map.put("/user/accountCheck", "anon");
		

		map.put("/user/phoneLogin", "anon");
		map.put("/user/code", "anon");
		map.put("/user/accountLogin", "anon");
		map.put("/aaa.html", "user");
	
		map.put("/logout", "logout");
		map.put("/login/logout", "logout");		
		/*map.put("/**", "authc");*/

		
		
		bean.setFilterChainDefinitionMap(map);
		return bean;
	}
}
