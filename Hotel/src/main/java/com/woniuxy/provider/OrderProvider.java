package com.woniuxy.provider;

import org.apache.ibatis.jdbc.SQL;

import com.woniuxy.pojo.Order;

public class OrderProvider {
	
	public String select(Order order){
		SQL sql=new SQL().SELECT("*").FROM("`order`");
		if(order.getOrder_id()!=null){
			sql.WHERE("order_id="+order.getOrder_id());
		}
		if(order.getLogin_id()!=null){
			sql.WHERE("login_id="+order.getLogin_id());
		}
		
		return sql.toString();
	}
	
	public String insert(Order order){
		
		SQL sql=new SQL();
		sql.INSERT_INTO("`order`").VALUES("creat_time","'"+order.getCreat_time()+"'").VALUES("order_number", "'"+order.getOrder_number()+"'").VALUES("in_time","'"+ order.getIn_time()+"'");
		sql.VALUES("out_time","'"+ order.getOut_time()+"'").VALUES("cancel_time","'"+order.getCancel_time()+"'").VALUES("sumprice", order.getSumprice().toString());
		if(order.getIn_air()!=null){
			sql.VALUES("in_air", order.getIn_air().toString());
		}
		if(order.getArrive_time()!=null){
			sql.VALUES("arrive_time","'"+ order.getArrive_time()+"'");
		}
		if(order.getMsg()!=null){
			sql.VALUES("msg","'"+ order.getMsg()+"'");
		}
		if(order.getLogin_id()!=null){
			sql.VALUES("login_id", order.getLogin_id().toString());
		}
		if(order.getMember_id()!=null){
			sql.VALUES("member_id", order.getMember_id().toString());
		}
		
		return sql.toString();
	}

}
