package com.woniuxy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.woniuxy.pojo.Member;

public interface MemberDao {
	@Insert("insert into member(name,tel,idcard,grade,rank,falg) values(#{name},#{tel},#{idcard},#{grade},#{rank},0)")
	public void insert(Member member);
	
	@Update("update member set falg=1 where member_id=#{member_id} and falg=0")
	public int deleteById(Member member);
	
	@Select("select * from member where member_id=#{member_id} and falg=0")
	public Member findOneById(Integer member_id);
	
	@Select("select * from member where `name`=#{name} and falg=0")
	public Member findOneByName(String name);
	
	@Select("select * from member where falg=0")
	public List<Member> findAll();
	
	
	/**
	 * 修改:这里需要使用动态SQL
	 */
	
	/**
	 * 更新积分
	 */
	@Update("update member set grade=#{grade} where idcard=#{idcard} and falg=0")
	public void updateGrade(Member member);
	
	/**
	 * 更新会员等级
	 */
	@Update("update member set rank=#{rank} where idcard=#{idcard} and falg=0")
	public void updateRank(Member member);
	@Update("update member set tel=#{tel} where idcard=#{idcard} and falg=0")
	public void undateTel(Member member);

}
