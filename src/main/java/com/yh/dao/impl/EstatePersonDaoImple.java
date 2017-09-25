package com.yh.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yh.dao.EstatePersonDao;
import com.yh.model.Estate;
import com.yh.util.MyConnection;


public class EstatePersonDaoImple implements EstatePersonDao{
	//插入新员工
	@Override
	public boolean insertEstatePersonByObj(Estate es) {
		boolean flag=false;
		Connection con = MyConnection.getConnection();
		StringBuffer bf = new StringBuffer("insert into estateperson_info(E_name,E_phone,E_id,E_starttime) ");
		bf.append("VALUES(?,?,?,?) ");
		try {
			PreparedStatement pst = con.prepareStatement(bf.toString());
			pst.setString(1, es.getEname());
			pst.setString(2, es.getEphone());
			pst.setString(3, es.getEid());
			pst.setString(4, es.getCurrentTime());
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
	//查询员工
	@Override
	public List<Estate> SelectEstatePersonByObj(Estate e,int page,int pageSize) {
		List<Estate> Estates=new ArrayList<Estate>();
		Connection con = MyConnection.getConnection();
		StringBuffer bf = new StringBuffer("select * from estateperson_info ");
		bf.append("where E_name like concat('%', ? ,'%') and ");
		bf.append(" E_phone like concat('%', ? ,'%') and ");
		bf.append(" E_id like concat('%', ? ,'%') and ");
		bf.append(" E_starttime like concat('%', ? ,'%') ");
		bf.append("limit ?,?");
		PreparedStatement pst;
		try {
			pst = con.prepareStatement(bf.toString());
			pst.setString(1, e.getEname());
			pst.setString(2, e.getEphone());
			pst.setString(3, e.getEid());
			pst.setString(4, e.getCurrentTime());
			pst.setInt(5, (page-1)*pageSize);
			pst.setInt(6, pageSize);
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{	
				Estate es=new Estate();
				es.setEno(rs.getInt("E_no"));
				es.setEname(rs.getString("E_name"));
				es.setEphone(rs.getString("E_phone"));
				es.setEid(rs.getString("E_id"));
				es.setCurrentTime(rs.getDate("E_starttime").toString());
				//System.out.println(rs.getDate("E_starttime").toString());
				//es.setQuitTime(rs.getDate("E_endtime").toString());
				Estates.add(es);	
			}
			pst.close();
			con.close();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return Estates;
	}
	//更新数据库
	@Override
	public boolean updateEstatePersonByObj(Estate e) {
		boolean flag=false;
		Connection con = MyConnection.getConnection();
		StringBuffer bf = new StringBuffer("update estateperson_info ");
		bf.append("set E_name=?,E_phone=?,E_id=? ");
		bf.append("where E_no=? ");
		try {
			PreparedStatement pst = con.prepareStatement(bf.toString());
			pst.setString(1, e.getEname());
			pst.setString(2, e.getEphone());
			pst.setString(3, e.getEid());
			pst.setInt(4, e.getEno());
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
	public boolean deleteEstatePersonByObj(Estate e) {
		boolean flag=false;
		Connection con = MyConnection.getConnection();
		StringBuffer bf = new StringBuffer("delete from estateperson_info ");
		bf.append("where E_no= ? ");
		try {
			PreparedStatement pst = con.prepareStatement(bf.toString());
			pst.setInt(1, e.getEno());
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

}
