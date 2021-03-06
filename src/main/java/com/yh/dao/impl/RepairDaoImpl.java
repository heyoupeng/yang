package com.yh.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yh.dao.RepairDao;
import com.yh.model.Repair;
import com.yh.util.MyConnection;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class RepairDaoImpl implements RepairDao {

	@Override
	public boolean insertRepair(Repair repair) {
		Connection con = MyConnection.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("insert into repair_info ");
		sql.append("(r_name,r_phone,r_id,r_starttime) ");
		sql.append("values(?,?,?,?) ");
		try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			pst.setString(1, repair.getName());
			pst.setString(2, repair.getPhone());
			pst.setString(3, repair.getId());
			java.sql.Date startTime = new java.sql.Date(repair.getStartTime().getTime());
			pst.setDate(4, startTime);
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteRepair(Repair repair) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateRepair(Repair repair) {
		Connection con = MyConnection.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("update repair_info ");
		sql.append("set r_name=?,r_phone=?,r_id=?,r_starttime=? ");
		sql.append("where r_no=? ");
		try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			pst.setString(1, repair.getName());
			pst.setString(2, repair.getPhone());
			pst.setString(3, repair.getId());
			java.sql.Date startTime = new java.sql.Date(repair.getStartTime().getTime());
			pst.setDate(4, startTime);
			pst.setInt(5, repair.getNo());
			pst.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Repair> getRepairs(int start, int number) {
		List<Repair> array = new ArrayList<Repair>();
		Connection con = MyConnection.getConnection();
		StringBuffer sql = new StringBuffer();
		// 获取学生信息的sql语句（分页）
		sql.append("select r_no,r_name,r_phone,r_id,r_starttime from repair_info ");
		sql.append("limit ?,?");
		try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			pst.setInt(1, start);// 开始位置
			pst.setInt(2, number);// 数量
			ResultSet rs = pst.executeQuery();
			Repair temp = null;
			while (rs.next()) {
				temp = new Repair();
				temp.setNo(rs.getInt("r_no"));
				temp.setName(rs.getString("r_name"));
				temp.setId(rs.getString("r_id"));
				temp.setPhone(rs.getString("r_phone"));
				if (rs.getDate("r_starttime") != null) {
					Date startTime = new Date(rs.getDate("r_starttime").getTime());
					temp.setStartTime(startTime);
				}
				array.add(temp);
			}
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (array.size() > 0) {
			return array;
		} else {
			return null;
		}
	}

	@Override
	public int getSum() {
		Connection con = MyConnection.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("select count(r_no) as sum ");
		sql.append("from repair_info ");
		int sum = 0;
		try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				sum = rs.getInt("sum");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sum;
	}
	
	

	@Override
	public boolean deleteRepairs(int[] no) {
		Connection con = MyConnection.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("delete from repair_info ");
		sql.append("where r_no=? ");
		try {
			con.setAutoCommit(false);
			PreparedStatement pst = con.prepareStatement(sql.toString());
			for (int i = 0; i < no.length; i++) {
				pst.setInt(1, no[i]);
				pst.addBatch();
			}
			pst.executeBatch();
			con.commit();
			con.setAutoCommit(true);
			return true;
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		try {
			con.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	
	/**
	 * tmc
	 */
	@Override
	public JSONArray getAllRair() {
		Connection con = MyConnection.getConnection();
		JSONArray jsonArray = new JSONArray();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM repair_info ");
		sql.append(" WHERE ISNULL(repair_info.R_endtime)");
		sql.append("and ( SELECT count(OW_id) as count ");
		sql.append("FROM ownerworkorders_info ");
		sql.append("where ISNULL(ownerworkorders_info.OW_endtime) ");
		sql.append("and ownerworkorders_info.R_no = repair_info.R_no) < 10");
		try {
			PreparedStatement pre = con.prepareStatement(sql.toString());
			ResultSet rs = pre.executeQuery();
			while(rs.next()){
				JSONObject json = new JSONObject();
				json.put("id", rs.getInt("R_no"));
				json.put("text", rs.getString("r_name"));
				jsonArray.add(json);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonArray;
	}

	@Override
	public List<Repair> getAllR_noAndR_name() {
		List<Repair> list = new ArrayList<Repair>();
		Connection con = MyConnection.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("select R_name,R_no from repair_info ");
		try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				Repair r = new Repair();
				r.setNo(rs.getInt("R_no"));
				r.setName(rs.getString("R_name"));
				list.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
