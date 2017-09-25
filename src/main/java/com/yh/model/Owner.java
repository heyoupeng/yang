package com.yh.model;
/**
 * 业主信息模型
 * @author ccy
 *
 */
public class Owner {
	private int O_ownerId;
  public int getO_ownerId() {
		return O_ownerId;
	}
	public void setO_ownerId(int o_ownerId) {
		O_ownerId = o_ownerId;
	}
private String  O_name;
  private String  O_id;
  private String O_phone;
  private String O_state;
  private String O_remark;
public String getO_name() {
	return O_name;
}
public void setO_name(String o_name) {
	O_name = o_name;
}
public String getO_id() {
	return O_id;
}
public void setO_id(String o_id) {
	O_id = o_id;
}
public String getO_phone() {
	return O_phone;
}
public void setO_phone(String o_phone) {
	O_phone = o_phone;
}
public String getO_state() {
	return O_state;
}
public void setO_state(String o_state) {
	O_state = o_state;
}
public String getO_remark() {
	return O_remark;
}
public void setO_remark(String o_remark) {
	O_remark = o_remark;
}
  
}
