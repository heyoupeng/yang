package com.yh.service.impl;

import java.util.Date;

import com.yh.dao.AccountDao;
import com.yh.dao.impl.AccountDaoImpl;
import com.yh.model.Account;
import com.yh.service.AccountService;

public class AccountServiceImpl implements AccountService{
	AccountDao accountDao =new AccountDaoImpl();
	@Override
	public boolean volicateAccount(String name, String password, String rid) {
		//记得添加rid测试
		return accountDao.volicateAccount(name, password, Integer.parseInt(rid));
	}
	
	@Override
	public Date isLocked(String name, String rid) {
		return accountDao.isLocked(name, rid);
	}

	@Override
	public boolean lock(String name, String rid) {
		return accountDao.lock(name, rid);
	}

	@Override
	public boolean updatePassword(Account acc, String password) {
		return accountDao.updatePassword(acc, password);
	}

}
