package com.yh.dao;

import java.util.List;

import org.w3c.dom.ls.LSException;

import com.yh.model.OwnerWorkOrderInfo;

public interface OwnerWorkOrderDao {
	public List<OwnerWorkOrderInfo> selectOrderByOId(String aname,int start,int pageSize);
	
	/**
	 * 该业主报修条数
	 * @param oid
	 * @return
	 */
	public int getSizeByOid(String aname);
	
	/**
	 * 未排遣维修人员的报修条数
	 * @return   未维修的条数
	 */
	public int getSizeRNoIsNull();
	
	/**
	 * 某个维修人员的维修单条数（如果all为true，查询所有，否则查询未完工的任务）
	 * @param rno  维修人员id
	 * @param all  是否是所有的数据
	 * @return	   
	 */
	public int getSizebyRNo(int rno, String state);
	
	public List<OwnerWorkOrderInfo> selectOrderByRNo(int rno,int start,int pageSize,String state);
	
	/**
	 * 新增一个报修
	 * @param info
	 * @return
	 */
	public boolean insertOrder(OwnerWorkOrderInfo info);
	
	/**
	 * 根据维护人员姓名查询id
	 * @param rname
	 * @return
	 */
	public int getRnoByRepairname(String rname);
	
	public int okOrder(int owid , String ow_result);	
	
	public List<OwnerWorkOrderInfo> selectAllOrder(String state ,int start , int pageSize);
	
	public int getOrderSize(String state);
	
	public boolean assignment(int owid, int rno);
	
	public int deleteOrder(int[] owids);

	
}
