package com.yh.dao;

import java.util.List;

import com.yh.model.Repair;

import net.sf.json.JSONArray;

public interface RepairDao {
	public boolean insertRepair(Repair repair);
	public boolean deleteRepair(Repair repair);
	public boolean updateRepair(Repair repair);
	public int getSum();
	public List<Repair> getRepairs(int start,int number);
}
