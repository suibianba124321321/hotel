package com.woniuxy.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;

import com.woniuxy.pojo.Information;

public interface InformationDao {
	//添加入住信息
	@Insert("")
	public int add(Information information);
	//修改入住信息
	public int update(Information information);
	//删除入住信息
	public int delete(Information information);
	//根据条件查询入住信息
	public List<Information> findList(Map<String,Object> queryMap);
	//模糊搜索总条数
	public Integer getTotal(Map<String,Object> queryMap);
	//根据information_id查询单个入住信息
	public Information findOne(Information information);
	

}
