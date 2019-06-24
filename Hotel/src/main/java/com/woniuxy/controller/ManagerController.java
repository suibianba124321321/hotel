package com.woniuxy.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woniuxy.pojo.Manager;
import com.woniuxy.service.ManagerService;
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
    //@ResponseBody
	@RequestMapping("/login")
	public String Login(Manager old_Manager){
		String login = managerService.login(old_Manager);
		if(login.equals("success")){
			return "redirect:/backstage/index.html";
		}
		return "success";
	}
	
	/**
	 * 根据账号修改密码
	 * @param manager
	 * @return
	 */
    @ResponseBody
	@RequestMapping("/updatePwd")
	public String changePwdByAccount(Manager manager){
		managerService.updatePwd(manager);
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
    
    /**
     * 根据账号删除员工
     * @param manager
     * @return
     */
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
    	managerService.insert(manager);
    	return "新增成功";
    }
	
	

}
