package com.yh.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.yh.dao.EstatePerson_infoDao;
import com.yh.util.MyConnection;

public class EstatePerson_infoDaoImpl implements EstatePerson_infoDao {

	@Override
	public int selectE_noByE_id(String E_id) {
		int i = 0;
		Connection con = MyConnection.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("select E_no from  EstatePerson_info ");
		sql.append("where  E_id=? ");
		try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			pst.setString(1,E_id);
			ResultSet rs = pst.executeQuery();
			if(rs.next())
			{
				i = rs.getInt("E_no");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public int insertEndtimeByE_no(int E_no) {
		int i = 0;
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(date);
		Connection con = MyConnection.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("update EstatePerson_info set E_endtime=? ");
		sql.append("where E_no = ? ");
		try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			pst.setString(1, time);
			pst.setInt(2, E_no);
			i = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

}
