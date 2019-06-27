package com.woniuxy.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.woniuxy.dao.MemberDAO;
import com.woniuxy.pojo.Member;
import com.woniuxy.service.MemberService;
@Service("memberService")
public class MemberServiceImpl implements MemberService {
	@Resource
	private MemberDAO memberDAO;
	@Override
	public Member findMemberByid(int id) {
		memberDAO.findMemberBId(id);
		return memberDAO.findMemberBId(id);
	}
	@Override
	public Map<String, Object> findMemberBymember(Member member) {
		Map<String, Object> map=new HashMap<>();
		map.put("msg", "成功");
		Member m=memberDAO.findMember(member);
		if(m==null){
			map.put("msg", "非会员");
		}else{
			map.put("id", m.getMember_id());
		}
		return map;
	}

}
