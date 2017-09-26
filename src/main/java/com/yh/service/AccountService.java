package com.yh.service;

import java.util.Date;

import com.yh.model.Account;

public interface AccountService {
	public boolean volicateAccount(String name,String password,String rid);
	public Date isLocked(String name,String rid);
	public boolean lock(String name,String rid);
	public boolean updatePassword(Account acc,String password);
}
