package com.yh.model;

/**
 * 小区物业物品实体类
 * @author 唐明成
 * @time 2017年9月21日
 */
public class VillageFacilityInfo {
	private int vNo;
	private String vName;
	private int vNumber;
	private String vRemark;
	private int vType;
	private String typeName;
	
	
	public VillageFacilityInfo() {
		super();
	}
	public VillageFacilityInfo(int vNo, String vName, int vNumber, String vRemark, int vType) {
		super();
		if(vType == 0){
			this.typeName = "小区物品";
		}
		else{
			this.typeName = "物业物品";
		}
		this.vNo = vNo;
		this.vName = vName;
		this.vNumber = vNumber;
		this.vRemark = vRemark;
		this.vType = vType;
	}
	
	
	
	public VillageFacilityInfo(String vName, int vNumber, String vRemark, String typeName) {
		super();
		this.vName = vName;
		this.vNumber = vNumber;
		this.vRemark = vRemark;
		if("小区物品".equals(typeName)){
			this.vType = 0;
		}
		else{
			this.vType = 1;
		}
		
	}
	public VillageFacilityInfo(int vNo, String vName, int vNumber, String vRemark, int vType, String typeName) {
		super();
		this.vNo = vNo;
		this.vName = vName;
		this.vNumber = vNumber;
		this.vRemark = vRemark;
		this.vType = vType;
		this.typeName = typeName;
	}
	
	public String getTypeName() {
		return typeName;
	}
	public int getvNo() {
		return vNo;
	}
	public void setvNo(int vNo) {
		this.vNo = vNo;
	}
	public String getvName() {
		return vName;
	}
	public void setvName(String vName) {
		this.vName = vName;
	}
	public int getvNumber() {
		return vNumber;
	}
	public void setvNumber(int vNumber) {
		this.vNumber = vNumber;
	}
	public String getvRemark() {
		return vRemark;
	}
	public void setvRemark(String vRemark) {
		this.vRemark = vRemark;
	}
	public int getvType() {
		return vType;
	}
	public void setvType(int vType) {
		this.vType = vType;
	}
	@Override
	public String toString() {
		return "VillageFacilityInfo [vNo=" + vNo + ", vName=" + vName + ", vNumber=" + vNumber + ", vRemark=" + vRemark
				+ ", vType=" + vType + "]";
	}
}
