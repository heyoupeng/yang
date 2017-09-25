package com.yh.service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Property_info房产信息表的各种服务
 * @author ccy
 *
 */
public interface Property_infoService {
	/**
	 * 获取datagrid的json格式。
	 * @param page  当前页码
	 * @param rows 每页行数
	 * @return  json格式
	 */
   public JSONObject getLimitOwner_info(String page,String rows,int buildNo,int unitNo,int floorNo,int roomNo);
   /**
    * 获得全部的楼栋号的JSONarray格式
    * @return
    */
   public JSONArray getP_bulid();
   /**
    * 获得全部的单元号的JSONarray格式
    * @return
    */
   public JSONArray getP_unit();
   /**
    * 获得全部的楼层号的JSONarray格式
    * @return
    */
   public JSONArray getP_floor();
   /**
    * 获得全部的房间号的JSONarray格式
    * @return
    */
   public JSONArray getP_room();
   /**
    * 添加房产信息
    * @param build
    * @param unit
    * @param floor
    * @param room
    * @param area
    * @param ownerId
    * @param remark
    * @return
    */
   public int insertProperty_info(int build,int unit,int floor,int room,double area,int ownerId,String remark);
   /**
    * 修改房产信息
    * @param build
    * @param unit
    * @param floor
    * @param room
    * @param area
    * @param ownerId
    * @param remark
    * @return
    */
   public int updateProperty_info(int build,int unit,int floor,int room,double area,int ownerId,String remark);
   /**
    * 删除房产信息
    * @param build
    * @param unit
    * @param floor
    * @param room
    * @return
    */
   public int deleteProperty_info(int build,int unit,int floor,int room);
}
