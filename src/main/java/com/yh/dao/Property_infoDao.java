package com.yh.dao;

import java.util.List;

import com.yh.model.Property;

/**
 * 对房产信息表（Property_info）的各种操作
 * @author ccy
 *
 */
public interface Property_infoDao {
	/**
	 * 根据条件获取Owner中数据总数
	 * @return
	 */
  public int getCount(int buildNo,int unitNo,int floorNo,int roomNo);
  /**
   * 根据条件分页查询
   * @param page
   * @param rows
   * @return
   */
  public List<Property>  limitProperty_info(int page,int rows,int buildNo,int unitNo,int floorNo,int roomNo);
  /**
   * 找到全部的P_build
   * @return
   */
  public List selectP_build();
  /**
   * 找到全部的P_unit
   * @return
   */
  public List selectP_unit();
  /**
   * 找到全部的P_floor
   * @return
   */
  public List selectP_floor();
  /**
   * 找到全部的P_room
   * @return
   */
  public List selectP_room();
  /**
   * 插入Property_info信息
   * @param build
   * @param unit
   * @param floor
   * @param room
   * @param area
   * @param ownerId
   * @param remark
   * @return
   */
  public int insertProperty_info(int build, int unit, int floor, int room, double area, int ownerId, String remark);
  /**
   * 修改Property_info信息
   * @param build
   * @param unit
   * @param floor
   * @param room
   * @param area
   * @param ownerId
   * @param remark
   * @return
   */
  public int updateProperty_info(int build, int unit, int floor, int room, double area, int ownerId, String remark);
  /**
   * 删除Property_info信息
   * @param build
   * @param unit
   * @param floor
   * @param room
   * @return
   */
  public int deleteProperty_info(int build, int unit, int floor, int room);
}
