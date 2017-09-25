package com.yh.service;

import java.util.List;
import com.yh.model.Estate;

public interface EstatePersonService {
	/**
	 * 添加物业人员
	 * @param e 添加物业人员对象
	 * @return
	 */
	public boolean addEstatePersonByObj(Estate e);
	/**
	 * 查询物业人员
	 * @param e 查询物业人员对象
	 * @return
	 */
	public List<Estate> searchEstatePersonByObj(Estate e,int page,int pageSize);
	/**
	 * 修改物业人员
	 * @param e 修改对象
	 * @return
	 */
	public boolean changeEstatePersonByObj(Estate e);
	/**
	 * 删除物业人员
	 * @param e 删除人员
	 * @return
	 */
	public boolean deleteEstatePersonByObj(Estate e);
}
