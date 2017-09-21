package com.yh.service.impl;

import com.yh.dao.RoleDao;
import com.yh.dao.impl.RoleDaoImpl;
import com.yh.service.RoleService;

import net.sf.json.JSONArray;

public class RoleServiceImpl implements RoleService{
	RoleDao roleDao=new RoleDaoImpl();
	@Override
	public JSONArray getAllRoles() {
		return roleDao.getAllRoles();
	}
	
}
