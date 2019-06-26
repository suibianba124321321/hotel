package com.woniuxy.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.woniuxy.pojo.Information;

public class InformationProvider {
	
	public String select(Map<String,Object> queryMap){
		Information information=(Information) queryMap.get("information");
		String name=(String) queryMap.get("name");
		int index=(int) queryMap.get("index");
		if(name==""){
			SQL sql=new SQL().SELECT("*").FROM("information").WHERE("flag =0");

		if(information.getIn_time()!=""){
			sql.WHERE("in_time='"+information.getIn_time()+"'");
		}
		if(information.getOut_time()!=""){
			sql.WHERE("out_time='"+information.getOut_time()+"'");
		}
		String preSql = sql.toString();
		StringBuilder sb = new StringBuilder(preSql);
		sb.append(" limit "+index+",5");
		return sb.toString();
			
		}else{
		SQL sql=new SQL().SELECT("*").FROM("information","person").WHERE("person.person_id = information.person_id and information.flag =0");

		if(information.getIn_time()!=""){
			sql.WHERE("in_time='"+information.getIn_time()+"'");
		}
		if(information.getOut_time()!=""){
			sql.WHERE("out_time='"+information.getOut_time()+"'");
		}
		if(name!=""){
			sql.WHERE("person.name = '"+name+"'");
		}
		
		String preSql = sql.toString();
		StringBuilder sb = new StringBuilder(preSql);
		sb.append(" limit "+index+",5");
		
		return sb.toString();
		}
	}
	
	
	public String selectCount(Map<String,Object> queryMap){
		Information information=(Information) queryMap.get("information");
		String name=(String) queryMap.get("name");
		if(name==""){
			SQL sql=new SQL().SELECT("COUNT(*)").FROM("information").WHERE("flag =0");
			if(information.getIn_time()!=""){
				sql.WHERE("in_time='"+information.getIn_time()+"'");
			}
			if(information.getOut_time()!=""){
				sql.WHERE("out_time='"+information.getOut_time()+"'");
			}
			return sql.toString();
			
		}else{
		SQL sql=new SQL().SELECT("COUNT(*)").FROM("information","person").WHERE("person.person_id = information.person_id and information.flag =0");
		if(information.getIn_time()!=""){
			sql.WHERE("in_time='"+information.getIn_time()+"'");
		}
		if(information.getOut_time()!=""){
			sql.WHERE("out_time='"+information.getOut_time()+"'");
		}
		if(name!=""){
		sql.WHERE("person.name = '"+name+"'");
		}
		return sql.toString();
		}
		
	}

}
