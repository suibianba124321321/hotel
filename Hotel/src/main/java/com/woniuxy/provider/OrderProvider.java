package com.woniuxy.provider;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.woniuxy.pojo.Login;
import com.woniuxy.pojo.Order;

public class OrderProvider {
	
	public String select(Order order){
		SQL sql=new SQL().SELECT("*").FROM("`order`");

		if(order.getMember_id()!=null){
			sql.WHERE("member_id="+order.getMember_id());
		}
		if(order.getLogin_id()!=null){
			sql.WHERE("login_id="+order.getLogin_id());
		}
		
		return sql.toString();
	}
	
	public String insert(Order order){
		
		SQL sql=new SQL();
		sql.INSERT_INTO("`order`").VALUES("creat_time","'"+order.getCreat_time()+"'").VALUES("order_number", "'"+order.getOrder_number()+"'").VALUES("in_time","'"+ order.getIn_time()+"'").VALUES("order_state","'"+ order.getOrder_state()+"'");
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

	public String selectBymap(Map<String, Object> map){
		SQL sql=new SQL().SELECT("*").FROM("`order`");
		if(map.get("state") !=null ){
			Integer state=(Integer) map.get("state");
			if(state !=100){
				sql.WHERE("order_state="+state);
			}
		}
		if(map.get("inTime") !=null ){
			String inTime=(String) map.get("inTime");
			if(!inTime.equals("")){
				sql.WHERE("in_time="+inTime);
			}
		}
		if(map.get("logins")!=null){
			List<Login> logins=(List<Login>) map.get("logins");
			if(logins.size()>0){
				StringBuffer login_id=new StringBuffer("(");
				for(int i=0;i<logins.size();i++){
					Integer loginId=logins.get(i).getLogin_id();
					
					if(i==(logins.size()-1)){
						login_id.append(loginId+")");
					}else{
						login_id.append(loginId+",");	
					}
				}
				sql.WHERE("login_id  in "+login_id);
			}
			
		}
		return sql.toString();
	}
	
	public String update(Order order){
		SQL sql =new SQL().UPDATE("`order`").WHERE("order_id="+order.getOrder_id());
		
			if(order.getIn_air()!=null){
				sql.SET("in_air="+order.getIn_air());
			}
			if(order.getArrive_time()!=null){
				sql.SET("arrive_time="+  "'"+order.getArrive_time()+"'");
			}
			if(order.getMsg()!=null){
				sql.SET("msg="+ "'"+order.getMsg()+"'");
			}
			if(order.getAuto_cancel()!=null){
				sql.SET("auto_cancel="+order.getAuto_cancel());
			}
		
		return sql.toString();
	}
}
