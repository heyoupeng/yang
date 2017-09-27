package com.yh.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yh.dao.QuitWork_infoDao;
import com.yh.model.Quitwok;
import com.yh.util.MyConnection;

public class QuitWork_infoDaoImpl implements QuitWork_infoDao{

	@Override
	public int propertyInsertInfo(int E_no, String content) {
		int i = 0;
		Date date=new Date();
		  DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  String time=format.format(date);
		Connection con = MyConnection.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("insert into quitwok_info (E_no,Q_content,Q_starttime,Q_result) ");
		sql.append("values (?,?,?,?)");
		try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			pst.setInt(1, E_no);
			pst.setString(2, content);
			pst.setString(3, time);
			pst.setString(4, "未审批");
			i = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public int getCount() {
		int i = 0;
		Connection con = MyConnection.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("select count(Q_no) as count from quitwok_info");
		try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			ResultSet rs = pst.executeQuery();
			if(rs.next())
			{
				i = rs.getInt("count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public List<Quitwok> limitQuitWork_info(int page, int rows) {
		 List<Quitwok> list  = new ArrayList<Quitwok>();
		 Connection con = MyConnection.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select Q_no,M_no, quitwok_info.E_no,E_name,R_no,Q_content,Q_starttime,Q_endtime,Q_result ");
			sql.append("from quitwok_info , estateperson_info where quitwok_info.E_no = estateperson_info.E_no ");
			sql.append("limit ?,?");
			try {
				PreparedStatement pst = con.prepareStatement(sql.toString());
				pst.setInt(1, page);
				pst.setInt(2, rows);
				ResultSet rs = pst.executeQuery();
				while(rs.next())
				{
					Quitwok q = new Quitwok();
					q.setE_no(rs.getInt("E_no"));
				    q.setM_no(rs.getInt("M_no"));
				    q.setQ_no(rs.getInt("Q_no"));
				    q.setR_no(rs.getInt("R_no"));
				    q.setQ_name(rs.getString("E_name"));
				    q.setQ_content(rs.getString("Q_content"));
				    q.setQ_starttime(rs.getString("Q_starttime"));
				    q.setQ_endtime(rs.getString("Q_endtime"));
				    q.setQ_result(rs.getString("Q_result"));
				    list.add(q);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 return list;
	}

	@Override
	public int updateQuitWork_infoByE_no(int E_no,String state,String result) {
		int i = 0;
		Date date=new Date();
		  DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  String time=format.format(date);
		Connection con = MyConnection.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("update quitwok_info set Q_result=?,Q_endtime=? ");
		sql.append("where E_no = ?");
		try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			pst.setString(1, "已审批:  "+state+"<br>备注： "+result);
			pst.setString(2, time);
			pst.setInt(3, E_no);
			i = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public List<String> selectMsgByE_no(int E_no) {
		List<String> list = new ArrayList<String>();
		Connection con = MyConnection.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("select Q_content,Q_starttime,Q_endtime,Q_result ");
		sql.append("from quitwok_info where E_no = ?");
		try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			pst.setInt(1, E_no);
			ResultSet rs = pst.executeQuery();
			if(rs.next())
			{
				list.add(rs.getString("Q_content"));
				list.add(rs.getString("Q_starttime"));
				list.add(rs.getString("Q_endtime"));
				list.add(rs.getString("Q_result"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
