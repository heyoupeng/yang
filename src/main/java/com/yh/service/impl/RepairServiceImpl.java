package com.yh.service.impl;

import java.util.List;

import com.yh.dao.RepairDao;
import com.yh.dao.impl.RepairDaoImpl;
import com.yh.model.Repair;
import com.yh.service.RepairService;

import net.sf.json.JSONObject;

public class RepairServiceImpl implements RepairService {
	RepairDao rd=new RepairDaoImpl();
	@Override
	public boolean insertRepair(Repair repair) {
		return rd.insertRepair(repair);
	}

	@Override
	public boolean deleteRepair(Repair repair) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateRepair(Repair repair) {
		return rd.updateRepair(repair);
	}

	@Override
	public JSONObject getDatagridRepairs(int start, int number) {
		List<Repair> list=rd.getRepairs(start, number);
		JSONObject obj=new JSONObject();
		obj.put("total", rd.getSum());
		obj.put("rows", list);
		return obj;
	}

}
