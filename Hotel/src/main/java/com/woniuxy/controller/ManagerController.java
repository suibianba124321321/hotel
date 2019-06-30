package com.woniuxy.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.crypto.hash.SimpleHashRequest;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woniuxy.datacenter.DataCenter;
import com.woniuxy.pojo.Manager;
import com.woniuxy.service.ManagerService;
import com.woniuxy.util.CustomizedToken;
import com.woniuxy.util.LoginType;

import ch.qos.logback.classic.pattern.Util;
/**
 * 后台人员controller操作   manager
 * @author Administrator
 * 
 * 1.超级管理员可以对普通管理员进行增删查改
 * 2.后台管理人员 可以进行系统设置 和营业查询
 *
 *  在后期密码加密的时候  在insert方法中 加密后传入管理员参数
 */

@Controller
@RequestMapping("/manager")
public class ManagerController {
	@Resource
	private ManagerService managerService;
	private static final String ADMIN_LOGIN_TYPE = LoginType.ADMIN.toString();
	
	public ManagerService getManagerService() {
		return managerService;
	}

	public void setManagerService(ManagerService managerService) {
		this.managerService = managerService;
	}
    /**
     * 登录操作
     * @param account  登录账号
     * @return 返回成功、失败
     */
    
	@RequestMapping("/login")
	public String Login(Manager old_Manager,HttpSession session){
		String login = managerService.login(old_Manager);
		
		  // 1、得到Subject
	    Subject subject = SecurityUtils.getSubject();

	    if(!subject.isAuthenticated()){
	    	
	    		CustomizedToken token = new CustomizedToken(old_Manager.getAccount(),old_Manager.getPwd(),ADMIN_LOGIN_TYPE );
	    		token.setRememberMe(false);
	    		try {	
	    			subject.login(token);
	    			session.setAttribute("account", old_Manager.getAccount());
	    			return "redirect:/backstage/index.html";
			} catch (IncorrectCredentialsException ice) {
				   System.out.println("密码不正确");
				    return "redirect:/backstage/login.html";
			}catch (AuthenticationException ae) {
                System.out.println(ae.getMessage());
                return "redirect:/backstage/error.html";
            }
	    	
	    }
		if(login.equals("success")){
			session.setAttribute("account", old_Manager.getAccount());
			return "redirect:/backstage/index.html";
		}
		return "redirect:/";
	}
	
	/**
	 * 根据账号修改密码
	 * @param manager
	 * @return
	 */
    @ResponseBody
	@RequestMapping("/updatePwd")
	public String changePwdByAccount(Manager manager){
    	String pwd = manager.getPwd();
    	manager.setPwd(new SimpleHash("MD5", pwd, null, 1024).toString());
		managerService.updatePwd(manager);
		return "success";
	}
    
    
    
    
    @ResponseBody
  	@RequestMapping("/updateall")
  	public String updateAll(Manager manager){
    	String pwd = manager.getPwd();
    	manager.setPwd(new SimpleHash("MD5", pwd, null, 1024).toString());
  		managerService.updateAll(manager);

  		return "success";
  	}
	

	/**
	 * 查询所有的员工信息
	 * @param manager
	 * @return
	 */
    @ResponseBody
	@RequestMapping("/all")
	public List<Manager> findAll(Manager manager){
		List<Manager> all = managerService.findAll();
		return all;
	}
	
    /**
     * 更具账号查询密码  在login中有这个方法  差啊村来的是一个对象
     */
    /**
     * 根据员工账号查询员工
     * @param manager
     * @return
     */
    @ResponseBody
	@RequestMapping("/findOneByAccount")
    public Manager findOneByAccount(Manager manager){
    	Manager one = managerService.findOneByAccount(manager);
    	return one;
    }
    
    
    @ResponseBody
	@RequestMapping("/findOneById")
    public Manager findOneById(Manager manager){
    	Manager one = managerService.findOneByID(manager);
    	return one;
    }
    
    
    
    /**
     * 根据账号删除员工
     * @param manager
     * @return
     */
    @RequiresRoles(value={"Superuser"})
    @ResponseBody
	@RequestMapping("/delete")
    public String deleteByID(Manager manager){
    	managerService.deleteByID(manager);
    	return "success";
    }

    
    /**
     * 根据账号删除员工
     * @param manager
     * @return
     */
    @ResponseBody
	@RequestMapping("/adds")
    public String add(Manager manager){
    	String pwd = manager.getPwd();
    	manager.setPwd(new SimpleHash("MD5", pwd, null, 1024).toString());
    	managerService.insert(manager);
    	return "新增成功";
    }
    
 
	

}
