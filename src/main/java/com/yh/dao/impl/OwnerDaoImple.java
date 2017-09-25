package com.yh.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yh.dao.OwnerDao;
import com.yh.util.MyConnection;

public class OwnerDaoImple implements OwnerDao{

	@Override
	public int selectOidByAccountID(String id) {
		int oid=0;
		Connection con = MyConnection.getConnection();
		StringBuffer bf = new StringBuffer("select * from owner_info ");
		bf.append("where O_id= ? ");
		PreparedStatement pst;
		try {
			pst = con.prepareStatement(bf.toString());
			pst.setString(1, id);
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{	
				oid=rs.getInt("O_ownerId");
			}
			pst.close();
			con.close();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		return oid;
	}

}
