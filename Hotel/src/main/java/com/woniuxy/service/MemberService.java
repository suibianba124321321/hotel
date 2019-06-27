package com.woniuxy.service;


import java.util.List;
import java.util.Map;

import com.woniuxy.pojo.Member;

public interface MemberService {
	
	public void insert(Member member);
	
	public void deleteById(Member member);
	
	public Member findOneById(Integer id);
	
	public List<Member> findAll();

	public Member findOneByName(String name);
	public void updateGrade(Member member);

	public void updateRank(Member member);

	public void undateTel(Member member);


  
	public Member findMemberByid(int id);

	public Map<String, Object> findMemberBymember(Member member);

}
