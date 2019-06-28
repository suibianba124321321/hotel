package com.woniuxy.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woniuxy.datacenter.DataCenter;

@Controller

public class DisCountController {
	
	/**
	 * 改变今日的折扣
	 * @param old_discount  老的折扣
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getdiscount")
	public Map<String, BigDecimal> getDiscount(){
		Map<String, BigDecimal> map = new HashMap<>();
        map.put("discount", DataCenter.getDiscount());
        map.put("on1", DataCenter.on1());
        map.put("on2", DataCenter.on2());
        map.put("on3", DataCenter.on3());
        map.put("down3", DataCenter.down3());
        map.put("down2", DataCenter.down2());
        map.put("down1", DataCenter.down1());
		return map;
	}
	@ResponseBody
	@RequestMapping("/setdiscount")
	public String setDiscount(String discount){
	  try {
		  BigDecimal bigDecimal = new BigDecimal(discount);
		  if(bigDecimal==null||bigDecimal.equals("0")){
				return "设置有误";
			}else{
				DataCenter.setDiscount(bigDecimal);
				return "修改成功";
			}
	} catch (Exception e) {
		return "输入格式错误";
	}
		
	  
	}
	
	

}
