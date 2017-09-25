package com.yh.dao;
/**
 * 对EstatePerson_info物业人员表的各种操作
 * @author ccy
 *
 */
public interface EstatePerson_infoDao {
	/**
	 * 通过登录账户名也就是E_id(物业人员的身份证号)找到其E_no（编号），在插入辞职信息时使用
	 * @param E_id
	 * @return
	 */
    public int selectE_noByE_id(String E_id);
    /**
     * 辞职申请通过后给该员工添加辞职事件
     * @param E_no
     * @return
     */
     public int insertEndtimeByE_no(int E_no);
}
