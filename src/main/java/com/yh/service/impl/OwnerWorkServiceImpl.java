package com.yh.service.impl;

import java.util.List;

import com.yh.dao.OwnerWorkOrderDao;
import com.yh.dao.impl.OwnerWorkOrderDaoImpl;
import com.yh.model.OwnerWorkOrderInfo;
import com.yh.service.OwnerWorkService;

import net.sf.json.JSONObject;

public class OwnerWorkServiceImpl implements OwnerWorkService{

	OwnerWorkOrderDao odao = new OwnerWorkOrderDaoImpl();
	@Override
	public JSONObject selectOrderByOId(String aname, int page, int pageSize) {
		int start = (page - 1 ) * pageSize;
		JSONObject json = new JSONObject();
		List<OwnerWorkOrderInfo> infos = odao.selectOrderByOId(aname, start, pageSize);
		json.put("total", odao.getSizeByOid(aname));
		json.put("rows", infos);
		return json;
	}

	@Override
	public JSONObject selectOrderByRNo(String aname, int page, int pageSize, String state) {
		int start = (page - 1 ) * pageSize;
		JSONObject json = new JSONObject();
		int rno = odao.getRnoByRepairname(aname);
		List<OwnerWorkOrderInfo> infos = odao.selectOrderByRNo(rno, start, pageSize, state);
		json.put("total", odao.getSizebyRNo(rno, state));
		json.put("rows", infos);
		return json;
	}

	@Override
	public boolean insertOrder(OwnerWorkOrderInfo info) {
		return odao.insertOrder(info);
	}

	@Override
	public int okOrder(int owid ,String ow_result) {
		return odao.okOrder(owid, ow_result);
	}

	@Override
	public JSONObject selectAllOrder(String state ,int page, int pageSize) {
		int start = (page - 1 ) * pageSize;
		JSONObject json = new JSONObject();
		List<OwnerWorkOrderInfo> infos = odao.selectAllOrder(state ,start, pageSize);
		json.put("total", odao.getOrderSize(state));
		json.put("rows", infos);
		return json;
	}

	@Override
	public boolean assignment(int owid, int rno) {
		return odao.assignment(owid, rno);
	}

	@Override
	public int deleteOrder(int[] owid) {
		return odao.deleteOrder(owid);
	}


}
