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
	
	//退房
	@Override
	public String check_out(Information information) {
		String result="退房失败";
		boolean re =informationDao.check_out(information);
		if(re){
			result="退房成功";
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
		List<Information> informations=null;
		int currentpage=(int) queryMap.get("currentpage");
		//根据页码获取下标
		int index=(currentpage-1)*5;
		queryMap.put("index", index);
		System.out.println(index);
		informations=informationDao.findList(queryMap);
		return informations;
	}

	//模糊搜索总条数
	@Override
	public Integer getTotal(Map<String, Object> queryMap) {
		int totalPage=0;
		int totalIndex=informationDao.getTotal(queryMap);
		totalPage=totalIndex%5==0?totalIndex/5:totalIndex/5+1;
		return totalPage;
	}

	//根据information_id查询单个入住信息
	@Override
	public Information findOne(Information information) {
		return informationDao.findOne(information);
	}

	//通过下标获取入住信息
	@Override
	public List<Information> findInformationByPage(int currentpage) {
		List<Information> informations=null;
		//根据页码获取下标
		int index=(currentpage-1)*5;
		informations=informationDao.findInformationByIndex(index);
		return informations;
	}
	
	//获取总页码
	@Override
	public int findTotalPage() {
		int totalPage=0;
		int totalIndex=informationDao.findTotalIndex();
		totalPage=totalIndex%5==0?totalIndex/5:totalIndex/5+1;
		return totalPage;
	}

	

}
