package com.yh.service.impl;

import com.yh.dao.FunctionDao;
import com.yh.dao.impl.FunctionDaoImpl;
import com.yh.service.FunctionService;

import net.sf.json.JSONArray;

public class FunctionServiceImpl implements FunctionService{
	FunctionDao functionDao=new FunctionDaoImpl();
	@Override
	public JSONArray getRoleFunctions(String rid) {
		return functionDao.getRoleFunctions(Integer.parseInt(rid));
	}

}
