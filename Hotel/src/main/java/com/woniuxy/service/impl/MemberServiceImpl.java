package com.woniuxy.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.woniuxy.dao.MemberDAO;
import com.woniuxy.pojo.Member;
import com.woniuxy.service.MemberService;
@Service("memberService")
public class MemberServiceImpl implements MemberService{
	@Resource
	private MemberDAO memberDao;

	@Override
	public void insert(Member member) {
		memberDao.insert(member);
		
	}

	@Override
	public Member findMemberByid(int id) {
		memberDao.findMemberBId(id);
		return memberDao.findMemberBId(id);
	}
	@Override
	public Map<String, Object> findMemberBymember(Member member) {
		Map<String, Object> map=new HashMap<>();
		map.put("msg", "成功");
		Member m=memberDao.findMember(member);
		if(m==null){
			map.put("msg", "非会员");
		}else{
			map.put("id", m.getMember_id());
		}
		return map;

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
	public void undateTel(Member member) {
		memberDao.undateTel(member);
		
	}


}
