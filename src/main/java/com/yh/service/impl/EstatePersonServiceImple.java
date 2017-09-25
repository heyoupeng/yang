package com.yh.service.impl;

import java.util.List;
import com.yh.dao.EstatePersonDao;
import com.yh.dao.impl.EstatePersonDaoImple;
import com.yh.model.Estate;
import com.yh.service.EstatePersonService;

import net.sf.json.JSONArray;

public class EstatePersonServiceImple implements EstatePersonService{
	EstatePersonDao epdao=new EstatePersonDaoImple();
	@Override
	public boolean addEstatePersonByObj(Estate e) {
		boolean flag=epdao.insertEstatePersonByObj(e);
		return flag;
	}
	@Override
	public List<Estate> searchEstatePersonByObj(Estate e,int page,int pageSize) {
		List<Estate> j=epdao.SelectEstatePersonByObj(e,page,pageSize);
		return j;
	}
	@Override
	public boolean changeEstatePersonByObj(Estate e) {
		boolean flag=epdao.updateEstatePersonByObj(e);
		return flag;
	}
	@Override
	public boolean deleteEstatePersonByObj(Estate e) {
		boolean flag=epdao.deleteEstatePersonByObj(e);
		return flag;
	}

}
