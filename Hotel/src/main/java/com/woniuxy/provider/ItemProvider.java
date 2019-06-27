package com.woniuxy.provider;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.woniuxy.pojo.Item;
import com.woniuxy.pojo.Person;

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
	
	public String selectBymap(Map<String, Object> map){
		SQL sql=new SQL().SELECT("*").FROM("item").WHERE("order_id="+map.get("orderid"));
		if(map.get("state")!=null){
			Integer state=(Integer) map.get("state");
			sql.WHERE("flag="+state);
		}
		if(map.get("persons")!=null){
			List<Person> persons=(List<Person>) map.get("persons");
			if(persons.size()>0){
				StringBuffer personid=new StringBuffer("(");
				for(int i=0;i<persons.size();i++){
					Integer id=persons.get(i).getPerson_id();
					
					if(i==(persons.size()-1)){
						personid.append(id+")");
					}else{
						personid.append(id+",");	
					}
				}
				
				sql.WHERE("person_id in "+personid);
			}
		}
		return sql.toString();
	}
}
