package com.yh.service;

import com.yh.model.Repair;

import net.sf.json.JSONObject;

public interface RepairService {
	public boolean insertRepair(Repair repair);
	public boolean deleteRepair(Repair repair);
	public boolean updateRepair(Repair repair);
	public JSONObject getDatagridRepairs(int start,int number);
}
