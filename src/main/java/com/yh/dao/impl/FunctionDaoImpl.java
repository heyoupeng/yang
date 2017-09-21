package com.yh.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yh.dao.FunctionDao;
import com.yh.util.MyConnection;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class FunctionDaoImpl implements FunctionDao{

	@Override
	public JSONArray getRoleFunctions(int rid) {
		Connection con=MyConnection.getConnection();
		JSONArray json=null;
		StringBuffer sql=new StringBuffer();
		sql.append("select r_fids from role ");
		sql.append("where r_id=? ");
		try {
			PreparedStatement pst=con.prepareStatement(sql.toString());
			pst.setInt(1, rid);
			ResultSet rs=pst.executeQuery();
			if(rs.next()){
				String fids=rs.getString("r_fids");
				json=getRoleFunctions(-1,fids);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return json;
	}

	
	public JSONArray getRoleFunctions(int parentId, String fids) {
		JSONArray userFuncs = getFuncsByParentId(parentId, fids);
		for (int i = 0; i < userFuncs.size(); i++) {
			String state = userFuncs.getJSONObject(i).getString("state");
			if ("open".equals(state)) {
				continue;
			} else {
				int thisId = userFuncs.getJSONObject(i).getInt("id");
				JSONArray chidFuncs = getRoleFunctions(thisId, fids);
				userFuncs.getJSONObject(i).put("children", chidFuncs);
			}
		}
		return userFuncs;
	}
	
	public JSONArray getFuncsByParentId(int parentId, String fids) {
		JSONArray array = new JSONArray();
		Connection con = MyConnection.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("select f_id,f_name,f_pid,f_state,f_path ");
		sql.append("from func ");
		sql.append("where ");
		sql.append("f_id in( ");
		sql.append(fids);
		sql.append(") ");
		sql.append("and f_pid=? ");
		try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			pst.setInt(1, parentId);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("id", rs.getInt("f_id"));
				obj.put("text", rs.getString("f_name"));
				obj.put("state", rs.getString("f_state"));
				JSONObject attr = new JSONObject();
				attr.put("fpath", rs.getString("f_path"));
				obj.put("attributes", attr);
				array.add(obj);
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return array;
	}


}
