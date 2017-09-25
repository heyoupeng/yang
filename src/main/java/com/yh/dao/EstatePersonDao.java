package com.yh.dao;

import java.util.List;

import com.yh.model.Estate;

public interface EstatePersonDao {
	/**
	 * 新物业人员插入数据库
	 * @param e 员工对象
	 * @return
	 */
	public boolean insertEstatePersonByObj(Estate e);
	/**
	 * 按条件查询数据库
	 * @param e 查询的对象
	 * @return
	 */
	public List<Estate> SelectEstatePersonByObj(Estate e,int page,int pageSize);
	/**
	 * 更新数据库信息
	 * @param e 更新对象
	 * @return
	 */
	public boolean updateEstatePersonByObj(Estate e);
	/**
	 * 删除数据库信息
	 * @param e 删除对象
	 * @return
	 */
	public boolean deleteEstatePersonByObj(Estate e);
}
