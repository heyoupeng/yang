package com.yh.service;

public interface OwnerService {
	/**
	 * 通过账户，找到owner的oid
	 * @param id 查找条件
	 * @return
	 */
	public int searchOidByAccountID(String id);
}
