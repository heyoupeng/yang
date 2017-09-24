package com.yh.dao;

import net.sf.json.JSONArray;

public interface FunctionDao {
	/**
	 * 获得属于该角色的权限列表（tree）
	 * @param rid	角色编号
	 * @return		权限
	 */
	public JSONArray getRoleFunctions(int rid);
	
	
}
