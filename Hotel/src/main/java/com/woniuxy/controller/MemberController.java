package com.woniuxy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.woniuxy.pojo.Login;
import com.woniuxy.pojo.Member;
import com.woniuxy.service.MemberService;
import com.woniuxy.utils.MemberUtil;

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
		// 更新 等级
		memberService.updateRank(member);
		
		return "success";
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
		return map;
	} 

}
