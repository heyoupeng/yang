package com.yh.dao;


import java.util.List;

import com.yh.model.Quitwok;

/**
 * 对QuitWork_info辞职信息表的各种操作
 * @author ccy
 *
 */
public interface QuitWork_infoDao {
	/**
	 * 物业人员提交辞职申请
	 * @param E_no
	 * @param content
	 * @return
	 */
    public int propertyInsertInfo(int E_no,String content);
    /**
     * 获取数据总数
     * @return
     */
    public int getCount();
    /**
     * 分页查询
     * @param page
     * @param rows
     * @return
     */
    public List<Quitwok> limitQuitWork_info(int page,int rows);
    /**
     * 经理审批物业人员的辞职申请
     * @param E_no
     * @param result
     * @return
     */
    public int updateQuitWork_infoByE_no(int E_no,String state,String result);
}
