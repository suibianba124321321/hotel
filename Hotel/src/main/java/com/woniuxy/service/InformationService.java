package com.woniuxy.service;

import java.util.List;
import java.util.Map;

import com.woniuxy.pojo.Information;

public interface InformationService {
	
		//添加入住信息
		public String add(Information information);
		//修改入住信息
		public String update(Information information);
		//退房
		public String check_out(Information information);
		//删除入住信息
		public String delete(Information information);
		//根据条件查询入住信息
		public List<Information> findList(Map<String,Object> queryMap);
		//模糊搜索总条数
		public Integer getTotal(Map<String,Object> queryMap);
		//根据information_id查询单个入住信息
		public Information findOne(Information information);
		//根据页码展示入住信息
		public List<Information> findInformationByPage(int currentpage);
		//计算总页码
		public int findTotalPage();

}
