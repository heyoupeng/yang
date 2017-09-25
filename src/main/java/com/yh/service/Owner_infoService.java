package com.yh.service;

import java.util.List;

import com.yh.model.Owner;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Owner_info表的一些服务
 * @author ccy
 *
 */
public interface Owner_infoService {
	/**
	 * 获取datagrid的json格式。
	 * @param page  当前页码
	 * @param rows 每页行数
	 * @return  json格式
	 */
   public JSONObject getLimitOwner_info(String page,String rows,String name,String id,String phone,String state);
   /**
    * 添加住户信息
    * @param name
    * @param id
    * @param phone
    * @param remark
    * @return
    */
   public int InsertOwener(String name,String id,String phone,String remark);
   /**
    * 修改住户信息
    * @param name
    * @param id
    * @param phone
    * @param remark
    * @return
    */
   public int UpdateOwener(String name,String id,String phone,String remark);
   /**
    * 修改住户状态，相当于移除该住户的信息
    * @param ids  需要修改状态的住户数组
    * @return
    */
   public int RemoveOwener(String ids[]);
   /**
    * 批量插入
    * @param list
    * @return
    */
   public boolean InsertMantOwner(List<Owner> list);
   /**
    * 获取全部的业主Id
    * @return
    */
   public JSONArray getO_ownerId();
}
