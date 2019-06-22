package com.woniuxy.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woniuxy.dao.InformationDao;
import com.woniuxy.pojo.Information;
import com.woniuxy.service.InformationService;
@Service("informationService")
public class InformationServiceImpl implements InformationService{
	@Autowired
	private InformationDao informationDao;
	
	//添加入住信息
	@Override
	public String add(Information information) {
		String result="添加失败";
		boolean re=informationDao.add(information);
		if(re){
			result="添加成功！";
		}
		return result;
	}

	//修改入住信息
	@Override
	public String update(Information information) {
		String result="修改失败";
		boolean re=informationDao.update(information);
		if(re){
			result="修改成功！";
		}
		return result;
	}

	//删除入住信息
	@Override
	public String delete(Information information) {
		String result="删除失败";
		boolean re=informationDao.delete(information);
		if(re){
			result="删除成功！";
		}
		return result;
	}

	//根据条件查询入住信息
	@Override
	public List<Information> findList(Map<String, Object> queryMap) {
		return informationDao.findList(queryMap);
	}

	//模糊搜索总条数
	@Override
	public Integer getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return informationDao.getTotal(queryMap);
	}

	//根据information_id查询单个入住信息
	@Override
	public Information findOne(Information information) {
		// TODO Auto-generated method stub
		return informationDao.findOne(information);
	}

}
