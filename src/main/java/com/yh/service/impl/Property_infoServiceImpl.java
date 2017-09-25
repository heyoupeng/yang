package com.yh.service.impl;

import java.util.List;

import com.yh.dao.Property_infoDao;
import com.yh.dao.impl.Property_infoDaoImpl;
import com.yh.model.Owner;
import com.yh.model.Property;
import com.yh.service.Property_infoService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Property_infoServiceImpl implements Property_infoService{
	Property_infoDao pdao = new Property_infoDaoImpl();
	@Override
	public JSONObject getLimitOwner_info(String page, String rows,int buildNo,int unitNo,int floorNo,int roomNo) {
		int i = pdao.getCount(buildNo,unitNo,floorNo,roomNo);
		List<Property> list = pdao.limitProperty_info((Integer.parseInt(page)-1)*3, Integer.parseInt(rows),buildNo,unitNo,floorNo,roomNo);
		JSONObject json = new JSONObject();
		json.put("total", i);
		json.put("rows", list);
		return json;
	}
	@Override
	public JSONArray getP_bulid() {
		JSONArray json = new JSONArray();
		List list = pdao.selectP_build();
		JSONObject a = new JSONObject();
		a.put("id", "");
		a.put("text", "无");
		json.add(a);
		for (Object o : list) {
			JSONObject j = new JSONObject();
			j.put("id", o);
			j.put("text", o);
			json.add(j);
		}
		return json;
	}
	@Override
	public JSONArray getP_unit() {
		JSONArray json = new JSONArray();
		List list = pdao.selectP_unit();
		JSONObject a = new JSONObject();
		a.put("id", "");
		a.put("text", "无");
		json.add(a);
		for (Object o : list) {
			JSONObject j = new JSONObject();
			j.put("id", o);
			j.put("text", o);
			json.add(j);
		}
		return json;
	}
	@Override
	public JSONArray getP_floor() {
		JSONArray json = new JSONArray();
		List list = pdao.selectP_floor();
		JSONObject a = new JSONObject();
		a.put("id", "");
		a.put("text", "无");
		json.add(a);
		for (Object o : list) {
			JSONObject j = new JSONObject();
			j.put("id", o);
			j.put("text", o);
			json.add(j);
		}
		return json;
	}
	@Override
	public JSONArray getP_room() {
		JSONArray json = new JSONArray();
		List list = pdao.selectP_room();
		JSONObject a = new JSONObject();
		a.put("id", "");
		a.put("text", "无");
		json.add(a);
		for (Object o : list) {
			JSONObject j = new JSONObject();
			j.put("id", o);
			j.put("text", o);
			json.add(j);
		}
		return json;
	}
	@Override
	public int insertProperty_info(int build, int unit, int floor, int room, double area, int ownerId, String remark) {
		int i = pdao.insertProperty_info(build, unit, floor, room, area, ownerId, remark);
		return i;
	}
	@Override
	public int updateProperty_info(int build, int unit, int floor, int room, double area, int ownerId, String remark) {
		int i = pdao.updateProperty_info(build, unit, floor, room, area, ownerId, remark);
		return i ;
	}
	@Override
	public int deleteProperty_info(int build, int unit, int floor, int room) {
		int i = pdao.deleteProperty_info(build, unit, floor, room);
		return i;
	}

}
