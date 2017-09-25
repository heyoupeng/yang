package com.yh.dao;

import net.sf.json.JSONArray;

public interface ManagerDao {
	/**
	 * 查询所有的经理编号
	 * @return
	 */
	public JSONArray getAllManagerNo();
	/**
	 * 通过身份证号查询经理编号
	 * @return
	 */
	public int getManangerNoByName(String name);
}
