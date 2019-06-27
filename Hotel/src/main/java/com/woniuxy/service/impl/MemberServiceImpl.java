package com.woniuxy.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.woniuxy.dao.MemberDAO;
import com.woniuxy.pojo.Member;
import com.woniuxy.service.MemberService;
@Service
public class MemberServiceImpl implements MemberService{
	@Resource
	private MemberDAO memberDao;

	@Override
	public void insert(Member member) {
		memberDao.insert(member);
		
	}

	@Override
	public void deleteById(Member member) {
		
		int i=memberDao.deleteById(member);
		System.out.println(i);
	}

	@Override
	public Member findOneById(Integer idcard) {
		Member member = memberDao.findOneById(idcard);
		return member;
	}

	@Override
	public List<Member> findAll() {
		List<Member> list = memberDao.findAll();
		return list;
	}



	@Override
	public void updateGrade(Member member) {
		memberDao.updateGrade(member);
		
	}

	@Override
	public void updateRank(Member member) {
		
		memberDao.updateRank(member);
	}

	@Override
	public Member findOneByName(String name) {
		Member member = memberDao.findOneByName(name);
		return member;
	}
	
	@Override
	public Member findMemberByid(Integer member_id) {
		return memberDao.findMemberByid(member_id);
	}

}
