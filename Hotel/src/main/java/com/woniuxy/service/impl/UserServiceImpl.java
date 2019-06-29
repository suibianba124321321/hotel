package com.woniuxy.service.impl;

import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woniuxy.dao.UserDao;
import com.woniuxy.pojo.Login;
import com.woniuxy.service.UserService;
import com.woniuxy.utils.Demo1;
import com.woniuxy.utils.JuHeDemo;
import com.woniuxy.utils.RegexUtil;

@Service("userService")
//开启事务
@Transactional
public class UserServiceImpl implements UserService{
     @Resource
	private UserDao userDao;
     
     //手机号码注册
	@Override
	public String phongRegisterCheck(String tel) {
		Login login=new Login();
		login.setTel(tel);
		String result="注册失败";
		//判断手机号码在数据库中存不存在
		String ifPhoneExit = userDao.ifPhoneExit(login);
		if(ifPhoneExit==null){
		boolean flag = userDao.phongRegister(tel);
		if(flag==true){
			result="注册成功";
		}
		}
		return result;
	}
    //账号密码注册
	@Override
	public String accountRegisterCheck(Login login) {
		String result="注册失败";
		if(login.getAccount()==null){
			return result;
		}
		else if(userDao.accountLogin(login)==null
				){
			boolean flag = userDao.accountRegister(login);
			if(flag==true){
				result="注册成功";
			}
		}
		return result;
	}
     //发送验证码
	@Override
	public String codeCheck(Login login,HttpSession session) {
       
		String result="发送失败";
		String tel=login.getTel();
		//判断手机号码格式判断
         if(!tel.matches(RegexUtil.REGEX_MOBILE)){
        	 return result;
         }
         
       //生成一个6位0~9之间的随机字符串
         StringBuffer rcode = new StringBuffer();
         Random random = new Random();
         for (int i = 0; i < 6; i++) {
        	 rcode.append(random.nextInt(10));
         }
         session.setAttribute("rcode", rcode);
         //我的模板id
         int tpl_id=169027;
         System.out.println("注册时验证码："+rcode);
         //调方法生成短信发送给tel
         boolean re = JuHeDemo.mobileQuery(tel, tpl_id, rcode);
         System.out.println(re);
         if(re){
        	 result="发送成功";
         }
		return result;
	}
   
	//账号密码登录
	@Override
	public String accountLogin(Login login) {
		String result="登录失败";
		String realPwd = userDao.accountLogin(login);
		if(realPwd!=null && realPwd==login.getPwd()){
			result="登录成功";
		}
		return result;
	}

	@Override
	public String checkPwdByaccount(Login login) {
		String pwd = userDao.accountLogin(login);
		return pwd;
	}
	
	//登录时验证手机号是否存在
	@Override
	public String ifPhoneExit(Login login,HttpSession session) {
		String result="账号不存在，请你先注册";
		String ifPhoneExit = userDao.ifPhoneExit(login);
		
		//生成一个6位0~9之间的随机字符串
        StringBuffer rcode = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
       	 rcode.append(random.nextInt(10));
        }
        session.setAttribute("logincode", rcode);
        //我的模板id
        int tpl_id=169027;
        
        System.out.println("登录时验证码："+rcode);
		if(ifPhoneExit!= null){
		boolean flag = JuHeDemo.mobileQuery(login.getTel(), tpl_id, rcode);
			if(flag){
				result="发送成功";
			}
		}
		return result;
	}
	@Override
	public Login findLoginByAccount(String account) {
		
		return userDao.findLoginByAccount(account);
	}
	@Override
	public Login findLoginByTel(String tel) {
		
		return userDao.findLoginByTel(tel);
	}
	

}