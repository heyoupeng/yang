package com.yh.service;

import com.yh.model.Repair;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface RepairService {
	public boolean insertRepair(Repair repair);
	public boolean deleteRepair(Repair repair);
	public boolean updateRepair(Repair repair);
	/**
	 * 
	 * @param nos 以','隔开的no数组
	 * @return
	 */
	public boolean deleteRepairs(String[] no);
	public JSONObject getDatagridRepairs(int start,int number);
	
	/**tmc
	 * 获取所有在职维修人员
	 * @return  下拉列表的json格式
	 */
	public JSONArray getAllRair();
}
