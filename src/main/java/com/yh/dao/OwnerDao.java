package com.yh.dao;

public interface OwnerDao {
	/**
	 * 查询oid
	 * @param id
	 * @return
	 */
	public int selectOidByAccountID(String id);
}
