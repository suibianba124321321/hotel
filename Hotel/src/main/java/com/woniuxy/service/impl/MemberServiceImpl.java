package com.woniuxy.service.impl;

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

}
