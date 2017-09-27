package com.yh.service;

import java.util.List;

import net.sf.json.JSONObject;

/**
 * 物业人员辞职功能模块
 * @author ccy
 *
 */
public interface QuitWork_infoService {
	/**
	 * 插入一条辞职申请
	 * @param username
	 * @param content
	 * @return
	 */
     public int propertyInsertQuitWork_info(String username,String content);
     /**
      * datagrid ,显示全部辞职申请
      * @param page
      * @param rows
      * @return
      */
     public JSONObject getLimitQuitWork_info(String page,String rows);
     /**
      * 经理审批物业人员的辞职申请,审批通过后给该员工添加辞职事件
      * @param id
      * @param result
      * @return
      */
     public int ApprovalQuitWork_info(String id,String state,String result);
     /**
      * 判断登录角色是否发起辞职申请角
      * @param name
      * @return
      */
     public List<String> judgePropertyResign(String name);
}
