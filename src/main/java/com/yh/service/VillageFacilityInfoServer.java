package com.yh.service;

import java.util.List;

import com.yh.model.VillageFacilityInfo;

import net.sf.json.JSONObject;

/**
 * 与小区物业物品有关的server
 * @author 唐明成
 * @time 2017年9月21日
 */
public interface VillageFacilityInfoServer {

	/**
	 * 添加一种小区物品
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
	 * 获取第几页的物品信息
	 * @param page			第几页
	 * @param pageSize		一页的数据量
	 * @return			            查询处来的VillageFacilityInfo的JSONObject
	 */
	public JSONObject selectVollageByPage(int page , int pageSize);
	/**
	 * 获取VillageFacilityInfo表一共有多少条数据
	 * @return  VillageFacilityInfo的数据总量
	 */
	public int selectVollageLen();
	
	/**
	 * 批量删除物品
	 * @param vnos  物品ID的int数组
	 * @return		删除成功条数
	 */
	public int deleteVollage(int[] vnos);
}
