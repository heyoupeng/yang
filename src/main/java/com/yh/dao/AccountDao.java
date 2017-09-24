package com.yh.dao;

public interface AccountDao {
	/**
	 * 验证登录
	 * @param name		用户名
	 * @param password	密码
	 * @param rid		角色
	 * @return			是否匹配
	 */
	public boolean volicateAccount(String name,String password,int rid);
}
