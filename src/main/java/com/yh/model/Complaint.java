package com.yh.model;

public class Complaint {
	private int cid;
	private int oid;
	private int mno;
	private String cremark;
	private String cstarttime;
	private String cendtime;
	private String cresult;
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getCremark() {
		return cremark;
	}
	public void setCremark(String cremark) {
		this.cremark = cremark;
	}
	public String getCstarttime() {
		return cstarttime;
	}
	public void setCstarttime(String cstarttime) {
		this.cstarttime = cstarttime;
	}
	public String getCendtime() {
		return cendtime;
	}
	public void setCendtime(String cendtime) {
		this.cendtime = cendtime;
	}
	public String getCresult() {
		return cresult;
	}
	public void setCresult(String cresult) {
		this.cresult = cresult;
	}
}
