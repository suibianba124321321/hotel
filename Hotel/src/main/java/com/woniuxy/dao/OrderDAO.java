package com.woniuxy.dao;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.woniuxy.pojo.Order;
import com.woniuxy.provider.OrderProvider;

@Repository
public interface OrderDAO {

	@InsertProvider(type=OrderProvider.class,method="insert")
	public void  insertOrder(Order order ); 
	
	@Update("update `order` set pay_number=#{pay_number},order_state=1 where order_number=#{order_number}")
	public  void updatePayNumber(Order order); 
	
	
	@SelectProvider(type=OrderProvider.class,method="select")
	public List<Order> findOrder( Order order );
	
	@Select("update  `order` set flag=1 ,order_state=4 where order_id=#{order_id}")
	public void deleteOrder(Order order);
	@Select("select * from `order` where order_number=#{orderNumber} and flag=0")
	public Order findOrderByNumber(String orderNumber);
	
}
