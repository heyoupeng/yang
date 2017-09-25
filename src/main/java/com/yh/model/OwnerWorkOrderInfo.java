package com.yh.model;

public class OwnerWorkOrderInfo {
	private int OW_id;
	private int O_ownerId;
	private String O_name;
	private String O_phone;
	private int R_no;
	private String OW_remark;
	private String OW_starttime;
	private String OW_endtime;
	private String OW_result;
	private String R_name;
	private String R_phone;
	private String state;

	public OwnerWorkOrderInfo() {
		super();
	}

	/**
	 * 申请维修时使用
	 */
	public OwnerWorkOrderInfo(int oW_id, int o_ownerId, String oW_remark) {
		super();
		OW_id = oW_id;
		O_ownerId = o_ownerId;
		OW_remark = oW_remark;
	}
	
	/**
	 * 业主查看维修信息是使用
	 */
	public OwnerWorkOrderInfo(int oW_id, String oW_remark, String oW_starttime, String oW_endtime, String oW_result,
			String r_name, String r_phone) {
		super();
		OW_id = oW_id;
		OW_remark = oW_remark;
		OW_starttime = oW_starttime;
		OW_endtime = oW_endtime;
		OW_result = oW_result;
		R_name = r_name;
		R_phone = r_phone;
	}
	/**
	 * 物业管理人员分配任务时
	 */
	public OwnerWorkOrderInfo(int oW_id, int o_ownerId, int r_no, String oW_remark, String oW_starttime, String oW_endtime,
			String oW_result) {
		super();
		OW_id = oW_id;
		O_ownerId = o_ownerId;
		R_no = r_no;
		OW_remark = oW_remark;
		OW_starttime = oW_starttime;
		OW_endtime = oW_endtime;
		OW_result = oW_result;
	}
	

	public OwnerWorkOrderInfo(int oW_id, int o_ownerId, String o_name, String o_phone, int r_no, String oW_remark,
			String oW_starttime, String oW_endtime, String oW_result, String r_name, String r_phone) {
		super();
		OW_id = oW_id;
		O_ownerId = o_ownerId;
		O_name = o_name;
		O_phone = o_phone;
		R_no = r_no;
		OW_remark = oW_remark;
		OW_starttime = oW_starttime;
		OW_endtime = oW_endtime;
		OW_result = oW_result;
		R_name = r_name;
		R_phone = r_phone;
	}

	public int getOW_id() {
		return OW_id;
	}

	public void setOW_id(int oW_id) {
		OW_id = oW_id;
	}

	public int getO_ownerId() {
		return O_ownerId;
	}

	public void setO_ownerId(int o_ownerId) {
		O_ownerId = o_ownerId;
	}

	public int getR_no() {
		return R_no;
	}
	
	
	public String getState() {
		return state;
	}
	public void setState(String state){
		this.state = state;
	}

	public void setR_no(int r_no) {
		R_no = r_no;
	}

	public String getOW_remark() {
		return OW_remark;
	}

	public void setOW_remark(String oW_remark) {
		OW_remark = oW_remark;
	}

	public String getOW_starttime() {
		return OW_starttime;
	}

	public void setOW_starttime(String oW_starttime) {
		OW_starttime = oW_starttime;
	}

	public String getOW_endtime() {
		return OW_endtime;
	}

	public void setOW_endtime(String oW_endtime) {
		OW_endtime = oW_endtime;
	}

	public String getOW_result() {
		return OW_result;
	}

	public void setOW_result(String oW_result) {
		OW_result = oW_result;
	}

	public String getO_name() {
		return O_name;
	}

	public String getO_phone() {
		return O_phone;
	}

	public String getR_name() {
		return R_name;
	}

	public String getR_phone() {
		return R_phone;
	}

	
	public void setO_name(String o_name) {
		O_name = o_name;
	}
	

	public void setO_phone(String o_phone) {
		O_phone = o_phone;
	}

	public void setR_name(String r_name) {
		R_name = r_name;
	}

	public void setR_phone(String r_phone) {
		R_phone = r_phone;
	}

	@Override
	public String toString() {
		return "OwnerWorkOrderInfo [OW_id=" + OW_id + ", O_ownerId=" + O_ownerId + ", O_name=" + O_name + ", O_phone="
				+ O_phone + ", R_no=" + R_no + ", OW_remark=" + OW_remark + ", OW_starttime=" + OW_starttime
				+ ", OW_endtime=" + OW_endtime + ", OW_result=" + OW_result + ", R_name=" + R_name + ", R_phone="
				+ R_phone + "]";
	}

	

}
