package com.woniuxy.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woniuxy.pojo.Login;
import com.woniuxy.service.UserService;

@RequestMapping("/user")
@Controller
public class UserController {
     @Resource
	private UserService userService;
     
     @RequestMapping("/phoneCheck")
     public String phongRegCheck(String tel,String code,HttpSession session){
    	 String result="注册失败";
    	 Object rcode = session.getAttribute("rcode");
    	 if(rcode!=null && code.equals(rcode.toString())){
    		 result = userService.phongRegisterCheck(tel);
      	       if(result.contains("成功")){
      	    	 return "redirect:../login.html";
      	       }
    	 }
    	      return "";
     }
     
     @RequestMapping("/accountCheck")
     public String accountRegCheck(Login login){
    	 login.setPwd(new SimpleHash("MD5",login.getPwd(),null,1024).toString());
    	 String result = userService.accountRegisterCheck(login); 
    	 if(result.contains("成功")){
    	 return "redirect:../login.html";}
    	 return "";
     }
     
     @RequestMapping("/sendMessage")
     @ResponseBody
     public String sendMessage(Login login,HttpSession session){
    	 String result="发送失败";
    	 result = userService.codeCheck(login, session);
    	 return "";
     }
     
     @RequestMapping("/accountLogin")
     @ResponseBody
     public String accountLogin(Login login){
    	 System.out.println(login);
    	//获取主体对象
 		Subject currentUser=SecurityUtils.getSubject();
 		if(!currentUser.isAuthenticated()){
 			UsernamePasswordToken token=
 					new UsernamePasswordToken(login.getAccount(),login.getPwd());
 			try {
 				currentUser.login(token);
 				System.out.println("登录成功");
 			} catch (Exception e) {
                 System.out.println("登录失败");
 			}
 		}
    	 return "";
     }
}
