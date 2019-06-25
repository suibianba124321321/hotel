package com.woniuxy.dao;

import org.apache.ibatis.annotations.Select;

import com.woniuxy.pojo.Member;

public interface MemberDAO {
@Select("select * from member where member_id=#{mid}")
public Member findMemberByid(Integer member_id);
}
