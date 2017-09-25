package com.yh.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yh.dao.ManagerDao;
import com.yh.service.ManagerService;
import com.yh.service.impl.ManagerServiceImple;
import com.yh.util.MyConnection;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ManagerDaoImple implements ManagerDao{
	
	@Override
	public JSONArray getAllManagerNo() {
		JSONArray json=new JSONArray();
		Connection con=MyConnection.getConnection();
		StringBuffer bf=new StringBuffer("select  DISTINCT M_no from manager_info ");
		try {
			PreparedStatement pst = con.prepareStatement(bf.toString());
			ResultSet rs =pst.executeQuery();
			int id=1;
			while(rs.next())
			{
				JSONObject  j =  new JSONObject();
				 j.put("id", id);
				 id++;
				 j.put("text", rs.getInt("M_no"));
				 json.add(j);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

	@Override
	public int getManangerNoByName(String name) {
		int mno=0;
		Connection con=MyConnection.getConnection();
		StringBuffer bf=new StringBuffer("select  DISTINCT M_no from manager_info ");
		bf.append(" where M_id= ?");
		try {
			PreparedStatement pst = con.prepareStatement(bf.toString());
			pst.setString(1, name);
			ResultSet rs =pst.executeQuery();
			int id=1;
			while(rs.next())
			{
				mno=rs.getInt("M_no");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mno;
	}

}
