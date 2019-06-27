package com.woniuxy.service;

import java.util.Map;

import com.woniuxy.pojo.Member;

public interface MemberService {
  
	public Member findMemberByid(int id);

	public Map<String, Object> findMemberBymember(Member member);
}
