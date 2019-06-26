package com.woniuxy.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woniuxy.pojo.Login;
import com.woniuxy.pojo.Member;
import com.woniuxy.service.MemberService;
import com.woniuxy.utils.MemberUtil;
@Controller
@RequestMapping("/member")
public class MemberController {
@Resource
private MemberService memberService;	
public MemberService getMemberService() {
	return memberService;
}
public void setMemberService(MemberService memberService) {
	this.memberService = memberService;
}





@RequestMapping("/currentMember")
@ResponseBody
public Map currentMember(HttpSession session){
	//从session中拿到member_id,通过member_id查询到当前会员	
	Object object = session.getAttribute("login");
	Login login=(Login) object;
	Map<String, Object> map = new HashMap<String, Object>();	
	Integer member_id=login.getMember_id();
	Member member = memberService.findMemberByid(member_id);
	String discount = MemberUtil.map.get(member.getRank());	
	map.put("member", member);
	map.put("discount", discount);
	return map;} 
}
