package com.yh.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import com.yh.dao.AccountDao;
import com.yh.model.Account;
import com.yh.util.MyConnection;

public class AccountDaoImpl implements AccountDao {

	@Override
	public boolean volicateAccount(String name,String password,int rid) {
		Connection con=MyConnection.getConnection();
		StringBuffer sql=new StringBuffer();
		sql.append("select A_locktime ");
		sql.append("from account ");
		sql.append("where A_name=? and A_password=? and r_id=? ");
		try {
			PreparedStatement pst=con.prepareStatement(sql.toString());
			pst.setString(1, name);
			pst.setString(2, password);
			pst.setInt(3, rid);
			ResultSet rs=pst.executeQuery();
			if(rs.next()){
				return true;
			}else{
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Date isLocked(String name,String rid) {
		Date nowTime=new Date();
		Date lockTime=null;
		Connection con=MyConnection.getConnection();
		StringBuffer sql=new StringBuffer();
		sql.append("select A_locktime ");
		sql.append("from account ");
		sql.append("where A_name=? and R_id=? ");
		try {
			PreparedStatement pst=con.prepareStatement(sql.toString());
			pst.setString(1, name);
			pst.setInt(2, Integer.parseInt(rid));
			ResultSet rs=pst.executeQuery();
			if(rs.next()){
				if(rs.getTime("A_locktime")!=null){
					
					lockTime=new Date(rs.getTimestamp("A_locktime").getTime());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(lockTime==null){
			return null;
		}else{
			if((nowTime.getTime()-lockTime.getTime())/1000.0>86400){
				return null;
			}else{
				Calendar cal=Calendar.getInstance();
				cal.setTime(lockTime);
				cal.add(Calendar.DAY_OF_YEAR, 1);
				lockTime.setTime(cal.getTimeInMillis());
				return lockTime;
			}
		}
	}
	@Override
	public boolean lock(String name, String rid) {
		Date nowTime=new Date();
		Connection con=MyConnection.getConnection();
		StringBuffer sql=new StringBuffer();
		sql.append("update account ");
		sql.append("set A_locktime=? ");
		sql.append("where A_name=? and R_id=?");
		try {
			PreparedStatement pst=con.prepareStatement(sql.toString());
			Timestamp ts=new Timestamp(nowTime.getTime()+24*60*60*1000);
			pst.setTimestamp(1, ts);
			pst.setString(2, name);
			pst.setInt(3, Integer.parseInt(rid));
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean updatePassword(Account acc, String password) {
		Connection con=MyConnection.getConnection();
		StringBuffer sql=new StringBuffer();
		sql.append("update account ");
		sql.append("set a_password=? ");
		sql.append("where r_id=? and a_name=? ");
		try {
			PreparedStatement pst=con.prepareStatement(sql.toString());
			pst.setString(1, password);
			pst.setInt(2,acc.getRid());
			pst.setString(3, acc.getUsername());
			int count=pst.executeUpdate();
			if(count>0){
				return true;
			}else{
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
