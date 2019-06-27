package com.woniuxy.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woniuxy.pojo.Member;
import com.woniuxy.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	@Resource
	private MemberService memberService;
	
	
	@RequestMapping("/findMember")
	@ResponseBody
	public Map<String, Object> searchMember(Member member){
		Map<String, Object> map =memberService.findMemberBymember(member);
		return map;
	}
}
