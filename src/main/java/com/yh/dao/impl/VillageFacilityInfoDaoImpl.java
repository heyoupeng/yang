package com.yh.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yh.dao.VillageFacilityInfoDao;
import com.yh.model.VillageFacilityInfo;
import com.yh.util.MyConnection;

public class VillageFacilityInfoDaoImpl implements VillageFacilityInfoDao {

	@Override
	public boolean insertVollage(VillageFacilityInfo village) {
		boolean ok = false;
		Connection con = MyConnection.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append(
				"INSERT into villagefaclity_info (villagefaclity_info.V_name,villagefaclity_info.V_number,villagefaclity_info.V_remark,villagefaclity_info.V_type) ");
		sql.append("VALUES(?,?,?,?)");

		try {
			PreparedStatement pre = con.prepareStatement(sql.toString());
			pre.setString(1, village.getvName());
			pre.setInt(2, village.getvNumber());
			pre.setString(3, village.getvRemark());
			pre.setInt(4, village.getvType());
			ok = pre.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ok;
	}

	@Override
	public boolean updateVollage(VillageFacilityInfo village) {
		boolean ok = false;
		Connection con = MyConnection.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE villagefaclity_info ");
		sql.append("set villagefaclity_info.V_name = ?, ");
		sql.append("villagefaclity_info.V_number = ?, ");
		sql.append("villagefaclity_info.V_remark = ?, ");
		sql.append("villagefaclity_info.V_type = ? " );
		sql.append("where villagefaclity_info.V_no  = ?");
		try {
			PreparedStatement pre = con.prepareStatement(sql.toString());
			pre.setString(1, village.getvName());
			pre.setInt(2, village.getvNumber());
			pre.setString(3, village.getvRemark());
			pre.setInt(4, village.getvType());
			pre.setInt(5, village.getvNo());
			ok = pre.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ok;
	}

	@Override
	public List<VillageFacilityInfo> selectVollageByPage(int start, int len) {
		List<VillageFacilityInfo> infos = new ArrayList<>();
		StringBuffer sql = new StringBuffer();
		Connection con = MyConnection.getConnection();
		sql.append(
				"SELECT villagefaclity_info.V_no,villagefaclity_info.V_name,villagefaclity_info.V_number,villagefaclity_info.V_remark,villagefaclity_info.V_type ");
		sql.append("FROM villagefaclity_info ");
		sql.append("LIMIT ?,?");
		try {
			PreparedStatement pre = con.prepareStatement(sql.toString());
			pre.setInt(1, start);
			pre.setInt(2, len);
			ResultSet rs = pre.executeQuery();
			while (rs.next()) {
				VillageFacilityInfo info = new VillageFacilityInfo(rs.getInt("V_no"), rs.getString("V_name"),
						rs.getInt("V_number"), rs.getString("V_remark"), rs.getInt("V_type"));
				infos.add(info);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return infos;
	}

	@Override
	public int selectVollageLen() {
		int len = 0;
		Connection con = MyConnection.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(villagefaclity_info.V_no) as len ");
		sql.append("from villagefaclity_info ");
		try {
			PreparedStatement pre = con.prepareStatement(sql.toString());
			ResultSet rs = pre.executeQuery();
			if (rs.next()) {
				len = rs.getInt("len");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return len;
	}

	@Override
	public int deleteVollage(int[] vnos) {
		int sum = 0;
		Connection con = MyConnection.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("delete from ");
		sql.append("villagefaclity_info where ");
		sql.append("villagefaclity_info.V_no = ?");
		try {
			con.setAutoCommit(false);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement pre;
		try {
			pre = con.prepareStatement(sql.toString());
			for (int i = 0; i < vnos.length; i++) {
				pre.setInt(1, vnos[i]);
				pre.addBatch();
			}
			int[] len = pre.executeBatch();
			con.commit();
			for (int i = 0; i < len.length; i++) {
				sum += len[i];
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return sum;
	}
	private boolean deleteVollageById(int vno){
		return false;
	}

}
