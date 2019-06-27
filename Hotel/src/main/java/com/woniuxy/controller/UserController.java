package com.woniuxy.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
     //手机号码注册
     @RequestMapping("/phoneCheck")
     @ResponseBody
     public String phongRegCheck(Login login,HttpSession session,HttpServletRequest request){
    	 String result="注册失败";
    	 Object rcode = session.getAttribute("rcode");
    	 Object code = request.getParameter("code");
    		 String rrcode=(String) code;
    	 if(rcode!=null && rrcode.equals(rcode.toString())){
    		 result = userService.phongRegisterCheck(login.getTel());
      	    	 return result;
    	 }
    	      return result;
     }
     //账号密码注册
     @RequestMapping("/accountCheck")
     @ResponseBody
     public String accountRegCheck(Login login){
    	 login.setPwd(new SimpleHash("MD5",login.getPwd(),null,1024).toString());
    	 String result = userService.accountRegisterCheck(login); 
    	 System.out.println(result);
    	 return result;
     }
     
     //注册发送验证码
     @RequestMapping("/sendMessage")
     @ResponseBody
     public String sendMessage(Login login,HttpSession session){
    	 System.out.println(89898);
    	 String result="发送失败";
    	 result = userService.codeCheck(login, session);
    	 return result;
     }
     
     //账号密码登录
     @RequestMapping("/accountLogin")
     @ResponseBody
     public String accountLogin(Login login,HttpSession session){
    	 System.out.println(login);
         String result="登录失败";
    	 //获取主体对象
 		Subject currentUser=SecurityUtils.getSubject();
 		if(!currentUser.isAuthenticated()){
 			UsernamePasswordToken token=
 					new UsernamePasswordToken(login.getAccount(),login.getPwd());
 			if (login.getRm()==true) {
 				System.out.println(666);
				token.setRememberMe(true);//记住我
				//第一次登陆之后，关闭了浏览器，可以保证在session有效期内下一次打开浏览器时
				//能够直接访问指定的页面   /url = user
			}
 			try {
 				currentUser.login(token);
// 				通过loginaccount查出当前login的所有信息封装并保存在session
 				Login login0 = userService.findLoginByAccount(login.getAccount());
 				session.setAttribute("login", login0);
 				 result="登录成功";
 				 
 			} catch (Exception e) {
 				result="登录失败";
 			}
 		}
 		System.out.println(result);
    	 return result;
     }
     
     //登录时手机号码验证
     @RequestMapping("/code")
     @ResponseBody
     public String offerCode(Login login,HttpSession session){
    	 String result = userService.ifPhoneExit(login, session);
    	 System.out.println(result);
    	 return result;
     }
     //手机号码登录
     @RequestMapping("/phoneLogin")
     @ResponseBody
     public String phoneRegister(Login login,HttpServletRequest request,HttpSession session){
    	 String result="登录失败";
    	 Object loginCode = session.getAttribute("logincode");
    	 String code = request.getParameter("code");
    	 if(loginCode==null){
    		 return result;
    	 }
    	 else{
    	 if(code.equals(loginCode.toString())){
    		 Login logino = userService.findLoginByTel(login.getTel());
    		 session.setAttribute("login", logino);
    		 result="登录成功";
    	 }
    	}
    	 System.out.println(result);
    	 return result;
     
     }
}
