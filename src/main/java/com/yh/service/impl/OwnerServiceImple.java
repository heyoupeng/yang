package com.yh.service.impl;

import com.yh.dao.OwnerDao;
import com.yh.dao.impl.OwnerDaoImple;
import com.yh.service.OwnerService;

public class OwnerServiceImple implements OwnerService{
	OwnerDao odao=new OwnerDaoImple();
	@Override
	public int searchOidByAccountID(String id) {
		int oid=odao.selectOidByAccountID(id);
		return oid;
	}

}
