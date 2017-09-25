package com.yh.service.impl;

import java.util.List;

import com.yh.dao.VillageFacilityInfoDao;
import com.yh.dao.impl.VillageFacilityInfoDaoImpl;
import com.yh.model.VillageFacilityInfo;
import com.yh.service.VillageFacilityInfoServer;

import net.sf.json.JSONObject;

public class VillageFacilityInfoServerImpl implements VillageFacilityInfoServer{

	VillageFacilityInfoDao infoDao = new VillageFacilityInfoDaoImpl();
	@Override
	public boolean insertVollage(VillageFacilityInfo village) {
		return infoDao.insertVollage(village);
	}

	@Override
	public boolean updateVollage(VillageFacilityInfo village) {
		// TODO Auto-generated method stub
		return infoDao.updateVollage(village);
	}

	@Override
	public JSONObject selectVollageByPage(int page, int pageSize) {
		// TODO Auto-generated method stub
		int start = (page - 1 ) * pageSize;
		JSONObject json = new JSONObject();
		json.put("total", infoDao.selectVollageLen());
		json.put("rows", infoDao.selectVollageByPage(start, pageSize));
		return json;
	}

	@Override
	public int selectVollageLen() {
		// TODO Auto-generated method stub
		return infoDao.selectVollageLen();
	}

	@Override
	public int deleteVollage(int[] vnos) {
		int len = infoDao.deleteVollage(vnos);
		return len ;
	}

}
