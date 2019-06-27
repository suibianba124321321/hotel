package com.woniuxy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import com.woniuxy.pojo.Member;
import com.woniuxy.provider.MemberProvice;
@Repository
public interface MemberDAO {

	@Select("select * from member where flag=0")
	public List<Member> allMember();
	@Select("select * from member where flag=0")
	public Member findMemberBId(int id);
	@SelectProvider(type=MemberProvice.class,method="select")
	public Member findMember(Member member);
}
