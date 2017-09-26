package com.yh.dao;

import java.util.Date;

import com.yh.model.Account;

public interface AccountDao {
	/**
	 * 验证登录
	 * @param name		用户名
	 * @param password	密码
	 * @param rid		角色
	 * @return			是否匹配
	 */
	public boolean volicateAccount(String name,String password,int rid);
	
	/**
	 * 判断账户是否已锁
	 * @param name		账户名
	 * @return			如果已锁，返回结束时间；如果未锁，返回null
	 */
	public Date isLocked(String name,String rid);
	
	/**
	 * 给用户上锁
	 * @param name	账户名
	 * @param rid	角色
	 */
	public boolean lock(String name,String rid);
	
	/**
	 * 更改角色密码
	 * @param acc	账户名
	 * @param password	角色
	 * @return
	 */
	public boolean updatePassword(Account acc,String password);

}
