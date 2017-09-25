package com.yh.model;
/**
 * 房产信息模型
 * @author ccy
 *
 */
public class Property {
      private int P_building;
      public int getP_building() {
		return P_building;
	}
	public void setP_building(int p_building) {
		P_building = p_building;
	}
	public int getP_unit() {
		return P_unit;
	}
	public void setP_unit(int p_unit) {
		P_unit = p_unit;
	}
	public int getP_floor() {
		return P_floor;
	}
	public void setP_floor(int p_floor) {
		P_floor = p_floor;
	}
	public int getP_room() {
		return P_room;
	}
	public void setP_room(int p_room) {
		P_room = p_room;
	}
	public int getP_ownerId() {
		return P_ownerId;
	}
	public void setP_ownerId(int p_ownerId) {
		P_ownerId = p_ownerId;
	}
	public double getP_area() {
		return P_area;
	}
	public void setP_area(double p_area) {
		P_area = p_area;
	}
	public String getP_remark() {
		return P_remark;
	}
	public void setP_remark(String p_remark) {
		P_remark = p_remark;
	}
	private int P_unit;
      private int P_floor;
      private int P_room;
      private int P_ownerId;
      private String P_ownerName;
      public String getP_ownerName() {
		return P_ownerName;
	}
	public void setP_ownerName(String p_ownerName) {
		P_ownerName = p_ownerName;
	}
	private double P_area;
      private String P_remark;
}
