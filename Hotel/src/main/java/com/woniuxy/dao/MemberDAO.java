package com.woniuxy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.woniuxy.pojo.Member;
@Repository
public interface MemberDAO {

	@Select("select * from member where flag=0")
	public List<Member> allMember();
	@Select("select * from member where flag=0")
	public Member findMemberBId(int id);
}
