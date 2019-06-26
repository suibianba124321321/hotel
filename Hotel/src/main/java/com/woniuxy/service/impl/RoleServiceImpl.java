package com.woniuxy.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.woniuxy.dao.RoleDAO;
import com.woniuxy.pojo.Role;
import com.woniuxy.service.RoleService;
@Service
public class RoleServiceImpl implements RoleService{
     @Resource
	private RoleDAO roleDAO;
	@Override
	public Role findRoleeById(Integer role_id) {
		Role role = roleDAO.findRoleeById(role_id);
		return role;
	}

}
