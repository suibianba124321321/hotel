package com.woniuxy.service;

import java.util.Map;

public interface BackService {

	//统计各项数据
	public Map<String, Object> number();

	public Map<String, Object> itemInformation(Integer id);
	
	
}
