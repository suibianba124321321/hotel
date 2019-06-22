package com.woniuxy.service.impl;

import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woniuxy.mapper.UserMapper;
import com.woniuxy.pojo.Login;
import com.woniuxy.service.UserService;
import com.woniuxy.utils.JuHeDemo;
import com.woniuxy.utils.RegexUtil;

@Service("userService")
//开启事务
@Transactional
public class UserServiceImpl implements UserService{
     @Resource
	private UserMapper userMapper;
	@Override
	public String phongRegisterCheck(String tel) {
		String result="注册失败";
		boolean flag = userMapper.phongRegister(tel);
		if(flag==true){
			result="注册成功";
		}
		return result;
	}

	@Override
	public String accountRegisterCheck(Login login) {
		String result="注册失败";
		boolean flag = userMapper.accountRegister(login);
		if(flag==true){
			result="注册成功";
		}
		return result;
	}
     //发送验证码
	@Override
	public String codeCheck(Login login,HttpSession session) {
       
		String result="手机号码有误";
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
         System.out.println(rcode);
         //我的模板id
         int tpl_id=166980;
         
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
		String realPwd = userMapper.accountLogin(login);
		if(realPwd!=null && realPwd==login.getPwd()){
			result="登录成功";
		}
		return result;
	}

	@Override
	public String checkPwdByaccount(Login login) {
		String pwd = userMapper.accountLogin(login);
		return pwd;
	}

}