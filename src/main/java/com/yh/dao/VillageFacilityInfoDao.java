package com.yh.dao;

import java.util.List;

import com.yh.model.VillageFacilityInfo;

/**
 * 与小区物业物品有关的dao
 * @author 唐明成
 * @time 2017年9月21日
 */
public interface VillageFacilityInfoDao {
	/**
	 * 插入一种小区物品
	 * @param village  小区物品
	 * @return		         插入物品是否成功
	 */
	public boolean insertVollage(VillageFacilityInfo village);
	/**
	 * 修改小区物品的信息
	 * @param village   小区物品
	 * @return 			插入物品是否成功
	 */
	public boolean updateVollage(VillageFacilityInfo village);
	/**
	 * 查询从start开始的len条数据
	 * @param start		开始位置
	 * @param len		需要查出的数据量
	 * @return			查询处来的VillageFacilityInfo集合
	 */
	public List<VillageFacilityInfo> selectVollageByPage(int start , int len);
	/**
	 * 查询VillageFacilityInfo表一共有多少条数据
	 * @return  VillageFacilityInfo的数据总量
	 */
	public int selectVollageLen();
	
	/**
	 * 批量删除物品
	 * @param vnos 物品id数组
	 * @return	         删除成功条数
	 */
	public int deleteVollage(int[] vnos);
}
