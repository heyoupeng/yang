package com.yh.service.impl;

import java.util.List;

import com.yh.dao.EstatePerson_infoDao;
import com.yh.dao.QuitWork_infoDao;
import com.yh.dao.impl.EstatePerson_infoDaoImpl;
import com.yh.dao.impl.QuitWork_infoDaoImpl;
import com.yh.model.Quitwok;
import com.yh.service.QuitWork_infoService;

import net.sf.json.JSONObject;

public class QuitWork_infoServiceImpl implements QuitWork_infoService{
	QuitWork_infoDao qdao = new QuitWork_infoDaoImpl();
	EstatePerson_infoDao edao = new EstatePerson_infoDaoImpl();
	@Override
	public int propertyInsertQuitWork_info(String username, String content) {
		int E_no = edao.selectE_noByE_id(username);
		int i = qdao.propertyInsertInfo(E_no, content);
		return i;
	}
	@Override
	public JSONObject getLimitQuitWork_info(String page, String rows) {
		JSONObject json = new JSONObject();
		int i = qdao.getCount();
		json.put("total", i);
		List<Quitwok> list = qdao.limitQuitWork_info((Integer.parseInt(page)-1)*3,Integer.parseInt(rows));
		json.put("rows", list);
		return json;
	}
	@Override
	public int ApprovalQuitWork_info(String id,String state, String result) {
		int i = qdao.updateQuitWork_infoByE_no(Integer.parseInt(id),state,result);
		if("通过".equals(state))
		{
		int j = edao.insertEndtimeByE_no(Integer.parseInt(id));
		}
		return i;
	}
	@Override
	public List<String> judgePropertyResign(String name) {
		 int E_no = edao.selectE_noByE_id(name);
		 List<String> list = qdao.selectMsgByE_no(E_no);
		 return list;
	}

}
