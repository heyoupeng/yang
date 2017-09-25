package com.yh.service;

import com.yh.model.OwnerWorkOrderInfo;

import net.sf.json.JSONObject;

public interface OwnerWorkService {
	/**
	 * 用户的所有报修
	 * @param oid 	            用户ID	
	 * @param page    	第几页
	 * @param pageSize  一页的数据量 
	 * @return
	 */
	public JSONObject selectOrderByOId(String aname,int page,int pageSize);
	
	public boolean insertOrder(OwnerWorkOrderInfo info);
	
	/**
	 * 维修人员完成任务
	 * @param owids  订单编号
	 * @return       修改成功多少条数据
	 */
	public int okOrder(int owids ,String ow_result);
	
	public JSONObject selectAllOrder(String state ,int page,int pageSize);
	
	/**
	 * 给某个人分配任务
	 * @param owid  订单号
	 * @param rno	维修人员编号
	 * @return
	 */
	public boolean assignment(int owid,int rno);
	
	public int deleteOrder(int[] owid);

	JSONObject selectOrderByRNo(String aname, int page, int pageSize, String state);
}
