package com.woniuxy.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.woniuxy.dao.MemberDAO;
import com.woniuxy.pojo.Member;
import com.woniuxy.service.MemberService;
@Service("memberServiceImpl")
public class MemberServiceImpl implements MemberService{
	@Resource
	private MemberDAO memberDAO;

	public MemberDAO getMemberDAO() {
		return memberDAO;
	}

	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	public Member findMemberByid(Integer member_id) {
		
		return memberDAO.findMemberByid(member_id);
	}

}
