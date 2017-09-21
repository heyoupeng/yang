package com.yh.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yh.dao.AccountDao;
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
	
}
