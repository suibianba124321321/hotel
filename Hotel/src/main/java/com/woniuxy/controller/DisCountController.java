package com.woniuxy.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woniuxy.datacenter.DataCenter;

@Controller
@RequiresRoles("/discount")
public class DisCountController {
	
	/**
	 * 改变今日的折扣
	 * @param old_discount  老的折扣
	 * @return
	 */
	@ResponseBody
	@RequiresRoles("/getDiscount")
	public Map<String, Double> changeDiscount(Double old_discount){
       Map<String, Double> map = getDisCountMessage();
		if(old_discount!=0){
			DataCenter.setDiscount(old_discount);
      	}else{
      		System.out.println("数据错误");
      	}	
		map.put("discount", DataCenter.getDiscount());	

		return map;
	}
	
	
	/**
	 * 所有会员的打折    on  代表线上    down  代表线下     1  2  3     普通会员   铂金会员   黑卡会员
	 * @return
	 */
	public Map<String,Double> getDisCountMessage(){
		Map<String,Double> map = new HashMap<>();
		/**
		 * 线上
		 */
		//普通会员打折
		map.put("on1", new DataCenter().on1());
		//铂金会员打折
		map.put("on2", new DataCenter().on2());
		//黑卡会员打折
		map.put("on3", new DataCenter().on3());
		
		/**
		 * 线下
		 */
		//普通会员打折
		map.put("on1", new DataCenter().down1());
		//铂金会员打折
		map.put("on2", new DataCenter().down2());
		//黑卡会员打折
		map.put("on3", new DataCenter().down3());
		
		return map;
	}

}
