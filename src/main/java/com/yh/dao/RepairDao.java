package com.yh.dao;

import java.util.List;

import com.yh.model.Repair;

import net.sf.json.JSONArray;

public interface RepairDao {
	/**
	 * 添加维修人员信息
	 * @param repair
	 * @return
	 */
	public boolean insertRepair(Repair repair);
	/**
	 * 删除维修人员信息
	 * @param repair
	 * @return
	 */
	public boolean deleteRepair(Repair repair);
	/**
	 * 删除维修人员信息
	 * @param no   编号组
	 * @return
	 */
	public boolean deleteRepairs(int[] no);
	/**
	 * 更新维修人员信息
	 * @param repair
	 * @return
	 */
	public boolean updateRepair(Repair repair);
	/**
	 * 获得维修人员总数
	 * @return
	 */
	public int getSum();
	/**
	 * 获得从第start个开始的number个维修人员信息
	 * @param start
	 * @param number
	 * @return
	 */
	public List<Repair> getRepairs(int start,int number);
	
	/**
	 * 查询所有的维修人员信息（tmc）
	 * @return
	 */
	public JSONArray getAllRair();
	/**
	 * 找到所有的维修人员的R_no
	 * @return
	 */
	public List<Repair> getAllR_noAndR_name();
	

}
