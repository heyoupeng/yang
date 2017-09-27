package com.yh.dao;

import java.util.List;
import java.util.Map;

import com.yh.model.Complaint;

public interface ComplaintDao {
	/**
	 * 查询工单
	 * @param c 查询对象
	 * @return
	 */
	public List<Complaint> selectComplaintByObj(Complaint c,int page,int pageSize);
	/**
	 * 查询工单
	 * @param c 查询对象
	 * @return
	 */
	public List<Complaint> selectComplaintByNothing(Complaint c,int page,int pageSize);
	/**
	 * 新的投诉插入数据库
	 * @param c
	 * @return
	 */
	public boolean insertComplaintByObj(Complaint c);
	/**
	 * 更新投诉数据库表
	 * @param c
	 * @return
	 */
	public boolean updateComplaintByObj(Complaint c);
	/**
	 * 删除数据库表
	 * @param cid
	 * @return
	 */
	public boolean deleteComplaintByCid(int cid);
	/**
	 * 经理查询投诉工单
	 * @param c 查询自己的
	 * @return
	 */
	public List<Complaint> selectComplaintByMySelf(Complaint c,int page,int pageSize);
	/**
	 * 经理查询投诉工单
	 * @param c 查询自己的
	 * @return
	 */
	public List<Complaint> selectComplaintByMySelfAll(Complaint c,int page,int pageSize);
	/**
	 * 通过cid受理投诉
	 * @param cid
	 * @return
	 */
	public boolean acceptComplaintsByCid(int cid);
	/**
	 * 更新结果
	 * @param c 结果对象
	 * @return
	 */
	public boolean updateComplaintsByObj(Complaint c);
	/**
	 *根据年份查找每个月的投诉工单数量，生成报表
	 * @return
	 */
	public Map selectAllMonths(String year);
}
