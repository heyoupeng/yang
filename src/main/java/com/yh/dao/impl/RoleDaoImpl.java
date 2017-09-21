package com.yh.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yh.dao.RoleDao;
import com.yh.util.MyConnection;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class RoleDaoImpl implements RoleDao{

	@Override
	public JSONArray getAllRoles() {
		Connection con=MyConnection.getConnection();
		JSONArray roles=new JSONArray();
		StringBuffer sql = new StringBuffer();
		sql.append("select distinct r_id,r_name ");
		sql.append("from role ");
		sql.append("where r_id is not null");
		try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			ResultSet rs = pst.executeQuery();
			boolean start = true;
			while (rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("id", rs.getInt("r_id"));
				obj.put("text", rs.getString("r_name"));
				if (start) {
					obj.put("selected", true);
					start = false;
				}
				roles.add(obj);
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return roles;
	}

}
