package com.woniuxy.service;

import java.util.List;

import com.woniuxy.pojo.Manager;

public interface ManagerService {
	Manager findOneByAccount(Manager old_Manager);
	String login(Manager old_Manager);
	
	public List<Manager> findAll();
	public void insert(Manager manager);
	public void updatePwd(Manager manager);
	public void deleteByName(Manager manager);
	public void deleteByID(Manager manager) ;
	Manager findOneByID(Manager manager);
}
