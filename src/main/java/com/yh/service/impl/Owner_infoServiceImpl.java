package com.yh.service.impl;

import java.util.List;

import com.yh.dao.Owner_infoDao;
import com.yh.dao.impl.Owner_infoDaoImpl;
import com.yh.model.Owner;
import com.yh.service.Owner_infoService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
/**
 * 实现Owner_infoService接口
 * @author ccy
 *
 */
public class Owner_infoServiceImpl implements Owner_infoService {
	Owner_infoDao odao = new Owner_infoDaoImpl();
	@Override
	public JSONObject getLimitOwner_info(String page, String rows,String name,String id,String phone,String state) {
		int i = odao.getCount(name,id,phone,state);
		List<Owner> list = odao.limitOwner_info((Integer.parseInt(page)-1)*3, Integer.parseInt(rows),name,id,phone,state);
		JSONObject json = new JSONObject();
		json.put("total", i);
		json.put("rows", list);
		return json;
	}
	@Override
	public int InsertOwener(String name, String id, String phone, String remark) {
		int i = odao.insertOwner(name, id, phone, remark);
		return i;
	}
	@Override
	public int UpdateOwener(String name, String id, String phone, String remark) {
		int i = odao.updateOwner(name, id, phone, remark);
		return i;
	}
	@Override
	public int RemoveOwener(String[] ids) {
		int j = 0;
		for (int i = 0; i < ids.length; i++) {
			 j = odao.removeOwner(ids[i]);
		}
		return j;
	}
	@Override
	public boolean InsertMantOwner(List<Owner> list) {
		boolean  flag = odao.insertManyOwner(list);
		return flag;
	}
	@Override
	public JSONArray getO_ownerId() {
		JSONArray json = new JSONArray();
		List list = odao.selectO_ownerId();
		for (Object o : list) {
			JSONObject j = new JSONObject();
			j.put("id", o);
			j.put("text", o);
			json.add(j);
		}
		return json;
	}

}
