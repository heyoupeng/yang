package com.yh.service.impl;

import com.yh.dao.ManagerDao;
import com.yh.dao.impl.ManagerDaoImple;
import com.yh.service.ManagerService;

import net.sf.json.JSONArray;

public class ManagerServiceImple implements ManagerService{
	ManagerDao mdao=new ManagerDaoImple();
	@Override
	public JSONArray getAllManagerNo() {
		JSONArray j=mdao.getAllManagerNo();
		return j;
	}
	@Override
	public int getManageNoByName(String name) {
		int mno=mdao.getManangerNoByName(name);
		return mno;
	}

}
