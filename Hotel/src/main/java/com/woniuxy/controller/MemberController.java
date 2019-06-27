package com.woniuxy.controller;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woniuxy.pojo.Member;
import com.woniuxy.service.MemberService;

/**
 * 会员类    对会员的增删查改
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/member")
public class MemberController {
	@Resource
	private MemberService memberService;
	
	@RequestMapping("/insert")
	@ResponseBody
	public String addMember(Member member){
		
		memberService.insert(member);
		
		return "添加成功，请刷新页面";
	}
	
	@RequestMapping("/all")
	@ResponseBody
	public List<Member> all(Member member){
		List<Member> findAll = memberService.findAll();
		return findAll;
	}
	

	
	@ResponseBody
	@RequestMapping("/find")
	public Member find(String name){
		
		try {
		Integer id=Integer.valueOf(name);
		Member member2 = memberService.findOneById(id);
		
		return member2;
		
		} catch (Exception e) {
			
			Member member2 = memberService.findOneByName(name);
			return member2;
		}
	
	}
	
	
	
	@RequestMapping("/findById")
	/**
	 * 这个方法目前还未完成
	 * @param member
	 * @param map
	 * @return
	 */
	@ResponseBody
	public Member findOneByName(Member member){
		
		Member findOneById = memberService.findOneById(member.getMember_id());
	 
		return findOneById;
	}
	
	
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(Member member){
		System.out.println(member);
		memberService.deleteById(member);
		return "success";
	}
	
	
	@RequestMapping("/update")
	@ResponseBody
	public String update(Member member){
		//修更新 积分
		memberService.updateGrade(member);
		//修改手机
		memberService.undateTel(member);
		
		return "success";
	}
	
	@RequestMapping("/findMember")
	@ResponseBody
	public Map<String, Object> searchMember(Member member){
		Map<String, Object> map =memberService.findMemberBymember(member);
		return map;
	}


}
