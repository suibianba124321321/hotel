package com.woniuxy.provider;

import org.apache.ibatis.jdbc.SQL;

import com.woniuxy.pojo.Item;

public class ItemProvider {
 
	public String updateByid(Item item){
		SQL sql=new SQL().UPDATE("item").WHERE("item_id="+item.getItem_id());
		if(item.getDay_number()!=null){
			sql.SET("day_number="+item.getDay_number());
		}
		if(item.getFlag()!=null){
			sql.SET("flag="+item.getFlag());
		}
		
		
		
		return sql.toString();
	}
}
