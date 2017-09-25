package com.yh.service;

import net.sf.json.JSONArray;

public interface ManagerService {
	/**
	 * 获得所有经理的编号
	 * @return
	 */
	public JSONArray getAllManagerNo();
	/**
	 * 通过登录名（身份证号）获得经理的编号
	 * @return
	 */
	public int getManageNoByName(String name);
}
