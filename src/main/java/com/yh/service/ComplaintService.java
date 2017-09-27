package com.yh.service;

import java.util.List;

import com.yh.model.Complaint;

public interface ComplaintService {
	/**
	 * 查询投诉工单
	 * @param c 查询对象
	 * @return
	 */
	public List<Complaint> searchComplaintByObj(Complaint c,int page,int pageSize);
	/**
	 * 发起新的投诉
	 * @param c
	 * @return
	 */
	public boolean insertComplaintByObj(Complaint c);
	/**
	 * 发起新的投诉
	 * @param c
	 * @return
	 */
	public boolean insertComplaintByObjNoEndtime(Complaint c);
	/**
	 * 修改投诉内容
	 * @param c
	 * @return
	 */
	public boolean changeComplaintByObj(Complaint c);
	/**
	 * 撤销投诉单
	 * @param cid
	 * @return
	 */
	public boolean revokeComplaintByCid(int cid);
	/**
	 * 查询住户投诉
	 * @param c 查询自己的
	 * @return
	 */
	public List<Complaint> searchComplaintByMySelf(Complaint c,int page,int pageSize); 
	/**
	 * 查询住户投诉
	 * @param c 查询自己的
	 * @return
	 */
	public List<Complaint> searchComplaintByMySelfAll(Complaint c,int page,int pageSize);
	/**
	 * 通过cid受理投诉
	 * @param cid
	 * @return
	 */
	public boolean acceptComplaintsByCid(int cid);
	/**
	 * 反馈结果
	 * @param c
	 * @return
	 */
	public boolean submitResultByObj(Complaint c);
}
