package com.yh.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yh.dao.OwnerWorkOrderDao;
import com.yh.model.OwnerWorkOrderInfo;
import com.yh.util.MyConnection;

public class OwnerWorkOrderDaoImpl implements OwnerWorkOrderDao {
	public static void main(String[] args) {
		OwnerWorkOrderDaoImpl i = new OwnerWorkOrderDaoImpl();
		/*List<OwnerWorkOrderInfo> l = i.selectOrderByOId(2, 0, 3);
		for (OwnerWorkOrderInfo ownerWorkOrderInfo : l) {
			System.out.println(ownerWorkOrderInfo);
		}*/
	}
	@Override
	public List<OwnerWorkOrderInfo> selectOrderByOId(String aname, int start, int pageSize) {
		StringBuffer sql = new StringBuffer();
		List<OwnerWorkOrderInfo> infos = new ArrayList<>();
		Connection con = MyConnection.getConnection();
		sql.append(
				"SELECT o_order.OW_id,o_order.OW_remark,o_order.OW_starttime,o_order.OW_endtime,owner_info.O_name,repair_info.R_name,repair_info.R_phone,o_order.OW_result ");
		sql.append("from ownerworkorders_info as o_order ");
		sql.append("LEFT JOIN owner_info on owner_info.O_ownerId = o_order.O_ownerId ");
		sql.append("LEFT JOIN repair_info on o_order.R_no = repair_info.R_no ");
		sql.append("where owner_info.O_id = ? ");
		sql.append("LIMIT ?,?");
		try {
			PreparedStatement pre = con.prepareStatement(sql.toString());
			pre.setString(1, aname);
			pre.setInt(2, start);
			pre.setInt(3, pageSize);
			ResultSet rs = pre.executeQuery();
			while (rs.next()) {
				OwnerWorkOrderInfo info = new OwnerWorkOrderInfo(rs.getInt("OW_id"), rs.getString("oW_remark"),
						rs.getString("oW_starttime"), rs.getString("oW_endtime"), rs.getString("oW_result"),
						rs.getString("r_name"), rs.getString("r_phone"));
				if(rs.getString("r_name") == null){
					info.setState("未分配维护人员");
				}
				else if(rs.getString("oW_endtime") == null){
					info.setState("维护人员未完成任务");
				}
				else{
					info.setState("维护人员已完成任务");
				}
				infos.add(info);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return infos;
	}

	@Override
	public int getSizeByOid(String aname) {
		StringBuffer sql = new StringBuffer();
		Connection con = MyConnection.getConnection();
		sql.append("SELECT COUNT(o_order.OW_id) as len ");
		sql.append("from ownerworkorders_info as o_order ");
		sql.append("LEFT JOIN owner_info on owner_info.O_ownerId = o_order.O_ownerId ");
		sql.append("LEFT JOIN repair_info on o_order.R_no = repair_info.R_id ");
		sql.append("where owner_info.O_id = ? ");
		try {
			PreparedStatement pre = con.prepareStatement(sql.toString());
			pre.setString(1, aname);
			ResultSet rs = pre.executeQuery();
			if (rs.next()) {
				return rs.getInt("len");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int getSizeRNoIsNull() {
		StringBuffer sql = new StringBuffer();
		Connection con = MyConnection.getConnection();
		sql.append("SELECT COUNT(ownerworkorders_info.OW_id) as len ");
		sql.append("from ownerworkorders_info ");
		sql.append("where  ISNULL(ownerworkorders_info.R_no) ");
		try {
			PreparedStatement pre = con.prepareStatement(sql.toString());
			ResultSet rs = pre.executeQuery();
			if (rs.next()) {
				return rs.getInt("len");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public int getSizebyRNo(int rno, String state) {
		StringBuffer sql = new StringBuffer();
		Connection con = MyConnection.getConnection();
		sql.append("SELECT COUNT(ownerworkorders_info.OW_id) as len ");
		sql.append("from ownerworkorders_info ");
		sql.append("where ownerworkorders_info.R_No = ? ");
		switch (state) {
		case "all":
			break;
		case "no":
			sql.append(" and ISNULL(ownerworkorders_info.OW_endtime) ");
			break;
		case "ok":
			sql.append(" and not ISNULL(ownerworkorders_info.OW_endtime) ");
			break;
		default:
			break;
		}
		try {
			PreparedStatement pre = con.prepareStatement(sql.toString());
			pre.setInt(1, rno);
			ResultSet rs = pre.executeQuery();
			if (rs.next()) {
				return rs.getInt("len");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public List<OwnerWorkOrderInfo> selectOrderByRNo(int rno, int start, int pageSize, String state) {
		StringBuffer sql = new StringBuffer();
		List<OwnerWorkOrderInfo> infos = new ArrayList<>();
		Connection con = MyConnection.getConnection();
		sql.append(
				"SELECT r_order.OW_id , r_order.OW_starttime , r_order.OW_endtime , r_order.OW_remark , owner_info.O_name , owner_info.O_phone ");
		sql.append("from ownerworkorders_info as r_order ");
		sql.append("LEFT JOIN owner_info on r_order.O_ownerId = owner_info.O_ownerId ");
		sql.append("where r_order.R_no = ? ");
		switch (state) {
		case "all":
			break;
		case "no":
			sql.append(" and ISNULL(r_order.OW_endtime) ");
			break;
		case "ok":
			sql.append(" and not ISNULL(r_order.OW_endtime) ");
			break;
		default:
			break;
		}
		sql.append("LIMIT ?,?");
		try {
			PreparedStatement pre = con.prepareStatement(sql.toString());
			pre.setInt(1, rno);
			pre.setInt(2, start);
			pre.setInt(3, pageSize);
			ResultSet rs = pre.executeQuery();
			while (rs.next()) {
				OwnerWorkOrderInfo info = new OwnerWorkOrderInfo();
				info.setOW_id(rs.getInt("OW_id"));
				info.setO_name(rs.getString("O_name"));
				info.setO_phone(rs.getString("O_phone"));
				info.setOW_remark(rs.getString("OW_remark"));
				info.setOW_starttime(rs.getString("OW_starttime"));
				if(rs.getString("oW_endtime") == null){
					info.setState("未完成任务");
				}
				else{
					info.setState("已完成任务");
				}
				infos.add(info);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return infos;
	}
	
	/**
	 * 根据业主用户名查询业主id
	 * @param rname
	 * @return
	 */
	private int getRnoByRname(String rname){
		StringBuffer sql = new StringBuffer();
		Connection con = MyConnection.getConnection();
		sql.append("SELECT owner_info.O_ownerId ");
		sql.append("FROM owner_info ");
		sql.append("where O_id = ? ");


		try {
			PreparedStatement pre = con.prepareStatement(sql.toString());
			pre.setString(1, rname);
			ResultSet rs = pre.executeQuery();
			if (rs.next()) {
				return rs.getInt("O_ownerId");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public int getRnoByRepairname(String rname){
		StringBuffer sql = new StringBuffer();
		Connection con = MyConnection.getConnection();
		sql.append("SELECT repair_info.R_no ");
		sql.append("FROM repair_info ");
		sql.append("where R_id = ? ");
		try {
			PreparedStatement pre = con.prepareStatement(sql.toString());
			pre.setString(1, rname);
			ResultSet rs = pre.executeQuery();
			if (rs.next()) {
				return rs.getInt("R_no");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public boolean insertOrder(OwnerWorkOrderInfo info) {
		boolean ok = false;
		StringBuffer sql = new StringBuffer();
		Connection con = MyConnection.getConnection();
		sql.append("INSERT into ownerworkorders_info ");
		sql.append("(ownerworkorders_info.O_ownerId , ownerworkorders_info.OW_remark , ownerworkorders_info.OW_starttime ) ");
		sql.append("VALUES(?, ? ,NOW()) ");
		try {
			PreparedStatement pre = con.prepareStatement(sql.toString());
			pre.setInt(1, this.getRnoByRname(info.getO_name()));
			pre.setString(2, info.getOW_remark());
			ok = pre.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ok;
	}
	@Override
	public int okOrder(int owids ,String ow_result) {
		int sum = 0;
		Connection con = MyConnection.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE ownerworkorders_info ");
		sql.append("set ownerworkorders_info.OW_endtime = NOW() , OW_result = ? ");
		sql.append("where ownerworkorders_info.OW_id =? and ISNULL(ownerworkorders_info.OW_endtime) ");
		try {
			PreparedStatement pre = con.prepareStatement(sql.toString());
			pre.setString(1, ow_result);
			pre.setInt(2, owids);
			sum = pre.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sum;
	}
	@Override
	public List<OwnerWorkOrderInfo> selectAllOrder(String state ,int start, int pageSize) {
		StringBuffer sql = new StringBuffer();
		List<OwnerWorkOrderInfo> infos = new ArrayList<>();
		Connection con = MyConnection.getConnection();
		sql.append(
				"SELECT r_order.OW_id , r_order.OW_starttime , r_order.OW_endtime , r_order.OW_remark , owner_info.O_name,repair_info.R_name ,r_order.R_no ");
		sql.append("from ownerworkorders_info as r_order ");
		sql.append("LEFT JOIN owner_info on r_order.O_ownerId = owner_info.O_ownerId ");
		sql.append("LEFT JOIN repair_info on r_order.R_no = repair_info.R_no ");
		switch (state) {
		case "all":
			break;
		case "no":
			sql.append(" where ISNULL(r_order.R_no) ");
			break;
		case "ok":
			sql.append(" where not ISNULL(r_order.R_no) ");
			break;
		default:
			break;
		}
		sql.append("ORDER BY r_order.R_no asc ");
		sql.append("LIMIT ?,?");
		try {
			PreparedStatement pre = con.prepareStatement(sql.toString());
			pre.setInt(1, start);
			pre.setInt(2, pageSize);
			ResultSet rs = pre.executeQuery();
			while (rs.next()) {
				OwnerWorkOrderInfo info = new OwnerWorkOrderInfo();
				info.setOW_id(rs.getInt("OW_id"));
				info.setO_name(rs.getString("O_name"));
				info.setR_name(rs.getString("R_name"));
				info.setOW_remark(rs.getString("OW_remark"));
				info.setOW_starttime(rs.getString("OW_starttime"));
				if(rs.getString("R_no") == null){
					info.setState("未分配维护人员");
				}
				else if(rs.getString("oW_endtime") == null){
					info.setState("维护人员未完成任务");
				}
				else{
					info.setState("维护人员已完成任务");
				}
				infos.add(info);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return infos;
	}
	@Override
	public int getOrderSize(String state) {
		StringBuffer sql = new StringBuffer();
		Connection con = MyConnection.getConnection();
		sql.append("SELECT COUNT(o_order.OW_id) as len ");
		sql.append("from ownerworkorders_info as o_order ");
		switch (state) {
		case "all":
			break;
		case "no":
			sql.append(" where ISNULL(o_order.R_no) ");
			break;
		case "ok":
			sql.append(" where not ISNULL(o_order.R_no) ");
			break;
		default:
			break;
		}
		try {
			PreparedStatement pre = con.prepareStatement(sql.toString());
			ResultSet rs = pre.executeQuery();
			if (rs.next()) {
				return rs.getInt("len");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public boolean assignment(int owid, int rno) {
		boolean ok = false;
		Connection con = MyConnection.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("update ownerworkorders_info ");
		sql.append("set R_no = ? ");
		sql.append("where OW_id = ?");
		try {
			PreparedStatement pre = con.prepareStatement(sql.toString());
			pre.setInt(1, rno);
			pre.setInt(2, owid);
			ok = pre.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ok;
	}
	@Override
	public int deleteOrder(int[] owids) {
		int sum = 0;
		Connection con = MyConnection.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("delete from ");
		sql.append("ownerworkorders_info where ");
		sql.append("ownerworkorders_info.OW_id = ? and isNull(ownerworkorders_info.OW_endtime) ");
		try {
			con.setAutoCommit(false);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement pre;
		try {
			pre = con.prepareStatement(sql.toString());
			for (int i = 0; i < owids.length; i++) {
				pre.setInt(1, owids[i]);
				pre.addBatch();
			}
			int[] len = pre.executeBatch();
			con.commit();
			for (int i = 0; i < len.length; i++) {
				sum += len[i];
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return sum;
	}
}
