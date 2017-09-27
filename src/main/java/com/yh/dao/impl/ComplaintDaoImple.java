package com.yh.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yh.dao.ComplaintDao;
import com.yh.model.Complaint;
import com.yh.util.MyConnection;

public class ComplaintDaoImple implements ComplaintDao{

	@Override
	public List<Complaint> selectComplaintByObj(Complaint c,int page,int pageSize) {
		List<Complaint> complaints=new ArrayList<Complaint>();
		Connection con = MyConnection.getConnection();
		StringBuffer bf = new StringBuffer("select * from complant_info  ");
		bf.append("where DATE_FORMAT(C_starttime,'%Y-%m-%d') LIKE CONCAT(?,'%')  ");
		bf.append(" and  M_no LIKE CONCAT('%',?,'%')  ");
		bf.append(" and  O_ownerId=?  ");
		bf.append("limit ?,?");
		PreparedStatement pst;
		try {
			pst = con.prepareStatement(bf.toString());
			pst.setString(1, c.getCstarttime());
			pst.setInt(2, c.getMno());
			pst.setInt(3, c.getOid());
			pst.setInt(4, (page-1)*pageSize);
			pst.setInt(5, pageSize);
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{	
				Complaint com=new Complaint();
				com.setCid(rs.getInt("C_id"));
				com.setOid(rs.getInt("O_ownerId"));
				com.setMno(rs.getInt("M_no"));
				com.setCremark(rs.getString("C_remark"));
				com.setCstarttime(rs.getString("C_starttime"));
				com.setCendtime(rs.getString("C_endtime"));
				com.setCresult(rs.getString("C_result"));;
				complaints.add(com);	
			}
			pst.close();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return complaints;
	}

	@Override
	public List<Complaint> selectComplaintByNothing(Complaint c,int page,int pageSize) {
		List<Complaint> complaints=new ArrayList<Complaint>();
		Connection con = MyConnection.getConnection();
		StringBuffer bf = new StringBuffer("select * from complant_info  ");
		bf.append("where DATE_FORMAT(C_starttime,'%Y-%m-%d') LIKE CONCAT(?,'%')  ");
		bf.append(" and  O_ownerId=?  ");
		bf.append("limit ?,?");
		PreparedStatement pst;
		try {
			pst = con.prepareStatement(bf.toString());
			pst.setString(1, c.getCstarttime());
			pst.setInt(2, c.getOid());
			pst.setInt(3, (page-1)*pageSize);
			pst.setInt(4, pageSize);
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{	
				Complaint com=new Complaint();
				com.setCid(rs.getInt("C_id"));
				com.setOid(rs.getInt("O_ownerId"));
				com.setMno(rs.getInt("M_no"));
				com.setCremark(rs.getString("C_remark"));
				com.setCstarttime(rs.getString("C_starttime"));
				com.setCendtime(rs.getString("C_endtime"));
				com.setCresult(rs.getString("C_result"));;
				complaints.add(com);	
			}
			pst.close();
			con.close();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return complaints;
	}

	@Override
	public boolean insertComplaintByObj(Complaint c) {
		boolean flag=false;
		Connection con = MyConnection.getConnection();
		StringBuffer bf = new StringBuffer("insert into complant_info(O_ownerId,M_no,C_remark,C_starttime,C_endtime) ");
		bf.append("VALUES(?,?,?,?,?) ");
		try {
			PreparedStatement pst = con.prepareStatement(bf.toString());
			pst.setInt(1, c.getOid());
			pst.setInt(2, c.getMno());
			pst.setString(3, c.getCremark());
			pst.setString(4, c.getCstarttime());
			pst.setString(5, c.getCendtime());
			int  rs = pst.executeUpdate();
			if(rs==1){
				flag=true;
			}
			else{
				flag=false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean updateComplaintByObj(Complaint c) {
		boolean flag=false;
		Connection con = MyConnection.getConnection();
		StringBuffer bf = new StringBuffer("update complant_info ");
		bf.append("set M_no=?,C_remark=?,C_endtime=? ");
		bf.append("WHERE C_id=? ");
		try {
			PreparedStatement pst = con.prepareStatement(bf.toString());
			pst.setInt(1, c.getMno());
			pst.setString(2, c.getCremark());
			pst.setString(3, c.getCendtime());
			pst.setInt(4, c.getCid());
			int  rs = pst.executeUpdate();
			if(rs==1){
				flag=true;
			}
			else{
				flag=false;
			}
			pst.close();
			con.close();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean deleteComplaintByCid(int cid) {
		boolean flag=false;
		Connection con = MyConnection.getConnection();
		StringBuffer bf = new StringBuffer("delete from complant_info ");
		bf.append("where C_id= ? ");
		try {
			PreparedStatement pst = con.prepareStatement(bf.toString());
			pst.setInt(1, cid);
			int  rs = pst.executeUpdate();
			if(rs==1){
				flag=true;
			}
			else{
				flag=false;
			}
			pst.close();
			con.close();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<Complaint> selectComplaintByMySelf(Complaint c,int page,int pageSize) {
		List<Complaint> complaints=new ArrayList<Complaint>();
		Connection con = MyConnection.getConnection();
		StringBuffer bf = new StringBuffer("select * from complant_info  ");
		bf.append("where M_no = ? ");
		bf.append(" and DATE_FORMAT(C_starttime,'%Y-%m-%d') LIKE CONCAT(?,'%')  ");
		bf.append(" and DATE_FORMAT(C_endtime,'%Y-%m-%d') LIKE CONCAT(?,'%')  ");
		bf.append(" and  O_ownerId=?  ");
		bf.append("limit ?,?");
		PreparedStatement pst;
		try {
			pst = con.prepareStatement(bf.toString());
			pst.setInt(1, c.getMno());
			pst.setString(2, c.getCstarttime());
			pst.setString(3, c.getCendtime());
			pst.setInt(4, c.getOid());
			pst.setInt(5, (page-1)*pageSize);
			pst.setInt(6, pageSize);
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{	
				Complaint com=new Complaint();
				com.setCid(rs.getInt("C_id"));
				com.setOid(rs.getInt("O_ownerId"));
				com.setMno(rs.getInt("M_no"));
				com.setCremark(rs.getString("C_remark"));
				com.setCstarttime(rs.getString("C_starttime"));
				com.setCendtime(rs.getString("C_endtime"));
				com.setCresult(rs.getString("C_result"));;
				complaints.add(com);	
			}
			pst.close();
			con.close();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return complaints;
	}

	@Override
	public List<Complaint> selectComplaintByMySelfAll(Complaint c,int page,int pageSize) {
		List<Complaint> complaints=new ArrayList<Complaint>();
		Connection con = MyConnection.getConnection();
		StringBuffer bf = new StringBuffer("select * from complant_info  ");
		bf.append("where M_no = ? ");
		bf.append(" and DATE_FORMAT(C_starttime,'%Y-%m-%d') LIKE CONCAT(?,'%')  ");
		bf.append(" and DATE_FORMAT(C_endtime,'%Y-%m-%d') LIKE CONCAT(?,'%')  ");
		bf.append("limit ?,? ");
		PreparedStatement pst;
		try {
			pst = con.prepareStatement(bf.toString());
			pst.setInt(1, c.getMno());
			pst.setString(2, c.getCstarttime());
			pst.setString(3, c.getCendtime());
			pst.setInt(4, (page-1)*pageSize);
			pst.setInt(5, pageSize);
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{	
				Complaint com=new Complaint();
				com.setCid(rs.getInt("C_id"));
				com.setOid(rs.getInt("O_ownerId"));
				com.setMno(rs.getInt("M_no"));
				com.setCremark(rs.getString("C_remark"));
				com.setCstarttime(rs.getString("C_starttime"));
				com.setCendtime(rs.getString("C_endtime"));
				com.setCresult(rs.getString("C_result"));;
				complaints.add(com);	
			}
			pst.close();
			con.close();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return complaints;
	}

	@Override
	public boolean acceptComplaintsByCid(int cid) {
		boolean flag=false;
		Connection con = MyConnection.getConnection();
		StringBuffer bf = new StringBuffer("update complant_info ");
		bf.append("set C_result=? ");
		bf.append("WHERE C_id=? ");
		try {
			PreparedStatement pst = con.prepareStatement(bf.toString());
			pst.setString(1, "已受理");
			pst.setInt(2, cid);
			int  rs = pst.executeUpdate();
			if(rs==1){
				flag=true;
			}
			else{
				flag=false;
			}
			pst.close();
			con.close();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean updateComplaintsByObj(Complaint c) {
		boolean flag=false;
		Connection con = MyConnection.getConnection();
		StringBuffer bf = new StringBuffer("update complant_info ");
		bf.append("set C_result=? ");
		bf.append("WHERE C_id=? ");
		try {
			PreparedStatement pst = con.prepareStatement(bf.toString());
			pst.setString(1, c.getCresult());
			pst.setInt(2, c.getCid());
			int  rs = pst.executeUpdate();
			if(rs==1){
				flag=true;
			}
			else{
				flag=false;
			}
			pst.close();
			con.close();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return flag;
	}

	@Override
	public Map selectAllMonths(String year) {
		Map map = new HashMap<Integer,Integer>();
		Connection con = MyConnection.getConnection();
		StringBuffer sql = new StringBuffer();
	   sql.append("SELECT  DATE_FORMAT(C_endtime,'%m') AS months,count(*) as count  ");
	   sql.append("FROM complant_info where C_endtime>? and C_endtime<? ");
	   sql.append("GROUP BY DATE_FORMAT(C_endtime,'%Y-%M') ORDER BY months");
		try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			pst.setString(1, year);
			pst.setString(2, Integer.toString((Integer.parseInt(year)+1)));
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				map.put(Integer.parseInt(rs.getString("months")), rs.getInt("count"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

}
