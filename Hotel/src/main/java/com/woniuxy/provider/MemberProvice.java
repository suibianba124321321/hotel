package com.woniuxy.provider;

import org.apache.ibatis.jdbc.SQL;

import com.woniuxy.pojo.Member;

public class MemberProvice {

	public String select(Member member){
		SQL sql=new SQL().SELECT("*").FROM("member").WHERE("flag=0");
		if(member.getName()!=null&& !member.getName().equals("")){
			
		}
		if(member.getTel()!=null && !member.getTel().equals("")){
			sql.WHERE("tel="+member.getTel());
		}
		if(member.getIdcard()!=null && !member.getIdcard().equals("")){
			sql.WHERE("idcard="+member.getIdcard());
		}
		return sql.toString();
		
	}
}
