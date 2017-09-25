package com.yh.dao;

import java.util.List;

import com.yh.model.Owner;


/**
 * 对Owner_info表的所有数据库操作
 * @author ccy
 *
 */
public interface Owner_infoDao {
	/**
	 * 根据条件获取Owner中数据总数
	 * @return
	 */
  public int getCount(String name,String id,String phone,String state);
  /**
   * 根据条件分页查询
   * @param page
   * @param rows
   * @return
   */
  public List<Owner>  limitOwner_info(int page,int rows,String name,String id,String phone,String state);
  /**
   * 添加单个用户信息
   * @param name
   * @param id
   * @param phone
   * @param remark
   * @return
   */
  public int insertOwner(String name, String id, String phone, String remark);
  /**
   * 修改单个用户信息
   * @param name
   * @param id
   * @param phone
   * @param remark
   * @return
   */
  public int updateOwner(String name, String id, String phone, String remark);
  /**
   * 修改单个用户信息的居住状态，类似于删除用户
   * @param id
   * @return
   */
  public int removeOwner(String id);
  /**
   * 批量插入
   * @param list
   * @return
   */
  public boolean insertManyOwner(List<Owner> list);
  /**
   * 获取全部的业主编号
   * @return
   */
  public List selectO_ownerId();
} 
