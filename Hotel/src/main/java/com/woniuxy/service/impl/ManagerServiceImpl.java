package com.woniuxy.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woniuxy.dao.ManagerDao;
import com.woniuxy.pojo.Manager;
import com.woniuxy.service.ManagerService;
@Service
public class ManagerServiceImpl implements ManagerService{
	@Autowired
	private ManagerDao managerDao;
	
   /**
    * 根据账号查询一个mananger
    */
	@Override
	public Manager findOneByAccount(Manager old_Manager) {
		Manager manager = managerDao.findOneByAccount(old_Manager.getAccount());
		return manager;
	}


	@Override
	public String login(Manager old_Manager) {
		String result="index";
		Manager account = findOneByAccount(old_Manager);
		if(account.getPwd().equals(old_Manager.getPwd())){
			result="success";
		}
		return result;
	}


	@Override
	public List<Manager> findAll() {
		List<Manager> list = managerDao.findAll();
		return list;
	}


	@Override
	public void insert(Manager manager) {

		managerDao.insert(manager);
	}


	@Override
	public void updatePwd(Manager manager) {
		managerDao.updatePwd(manager);
		
	}


	@Override
	public void deleteByName(Manager manager) {
		managerDao.deleteByName(manager);
		
	}
	
	@Override
	public void deleteByID(Manager manager) {
		managerDao.deleteByID(manager);
		
	}


	@Override
	public Manager findOneByID(Manager manager) {
		Manager byID = managerDao.findOneByID(manager);
		return byID;
	}

}
