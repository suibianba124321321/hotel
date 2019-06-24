package com.woniuxy.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
/*	@RequestMapping("/findByidcard")
	@ResponseBody
	public Member findOneByIdcard(Member member){
		Member member2 = memberService.findOneById(member.getIdcard());
		return member2;
	}*/
	
	
	@RequestMapping("/find")
	@ResponseBody
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
	
	
	
	@RequestMapping("/findByname")
	/**
	 * 这个方法目前还未完成
	 * @param member
	 * @param map
	 * @return
	 */
	public ModelAndView findOneByName(Member member,RedirectAttributes map){
		
		ModelAndView mo = new ModelAndView();
		Member member2 = memberService.findOneByName(member.getName());
		//mo.addObject("member", member2);
		map.addFlashAttribute("member", member2);
		System.out.println(member2);
		mo.setViewName("redirect:/backstage/member-select1.html?name=22");
		return mo;
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
		// 更新 等级
		memberService.updateRank(member);
		
		return "success";
	}
	
	

}
