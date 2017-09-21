package com.yh.service.impl;

import com.yh.dao.AccountDao;
import com.yh.dao.impl.AccountDaoImpl;
import com.yh.service.AccountService;

public class AccountServiceImpl implements AccountService{
	AccountDao accountDao =new AccountDaoImpl();
	@Override
	public boolean volicateAccount(String name, String password, String rid) {
		//记得添加rid测试
		return accountDao.volicateAccount(name, password, Integer.parseInt(rid));
	}

}
