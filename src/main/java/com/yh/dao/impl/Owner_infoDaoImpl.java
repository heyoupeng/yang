package com.yh.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yh.dao.Owner_infoDao;
import com.yh.model.Owner;
import com.yh.util.MyConnection;
/**
 * 实现Owner_infoDao接口
 * @author ccy
 *
 */
public class Owner_infoDaoImpl implements Owner_infoDao {

	@Override
	public int getCount(String name,String id,String phone,String state) {
		int i = 0;
		 Connection con = MyConnection.getConnection();
		 StringBuffer sql = new StringBuffer();
		 sql.append("select count(O_ownerId) as count from owner_info ");
		 sql.append("where O_name like ? and ") ;
		 sql.append("O_id like ? and ");
		 sql.append("O_phone like ? and ");
		 sql.append("O_state like ? ");
		 try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			pst.setString(1, '%'+name+'%');
			pst.setString(2, '%'+id+'%');
			pst.setString(3, '%'+phone+'%');
			pst.setString(4, '%'+state+'%');
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
	public List<Owner> limitOwner_info(int page, int rows,String name,String id,String phone,String state) {
		 List<Owner> list = new ArrayList<Owner>();
		 Connection con = MyConnection.getConnection();
		 StringBuffer sql = new StringBuffer();
		 sql.append("select O_ownerId,O_name,O_id,O_phone,O_state,O_remark ");
		 sql.append("from owner_info  ");
		 sql.append("where O_name like ? and ") ;
		 sql.append("O_id like ? and ");
		 sql.append("O_phone like ? and ");
		 sql.append("O_state like ? ");
		 sql.append("limit ?,? ");
		 try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			pst.setString(1, '%'+name+'%');
			pst.setString(2, '%'+id+'%');
			pst.setString(3, '%'+phone+'%');
			pst.setString(4, '%'+state+'%');
			pst.setInt(5, page);
			pst.setInt(6, rows);
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				Owner o = new Owner();
				o.setO_ownerId(rs.getInt("O_ownerId"));
				o.setO_name(rs.getString("O_name"));
				o.setO_id(rs.getString("O_id"));
				o.setO_phone(rs.getString("O_phone"));
				o.setO_state(rs.getString("O_state"));
				o.setO_remark(rs.getString("O_remark"));
				list.add(o);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return list;
	}

	@Override
	public int insertOwner(String name, String id, String phone, String remark) {
		int i = 0;
		 Connection con = MyConnection.getConnection();
		 StringBuffer sql = new StringBuffer();
		 sql.append("insert into owner_info (o_name,o_id,o_phone,o_state,o_remark ) ");
		 sql.append("values (?,?,?,?,?) ");
		 try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			pst.setString(1,name);
			pst.setString(2,id);
			pst.setString(3,phone);
			pst.setString(4,"居住");
			pst.setString(5,remark);
			i = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return i;
	}

	@Override
	public int updateOwner(String name, String id, String phone, String remark) {
		int i = 0;
		 Connection con = MyConnection.getConnection();
		 StringBuffer sql = new StringBuffer();
		 sql.append("update owner_info set o_name=?,o_phone=?,o_remark=? ");
		 sql.append("where o_id = ? ");
		 try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			pst.setString(1,name);
			pst.setString(2,phone);
			pst.setString(3,remark);
			pst.setString(4,id);
			i = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return i;
	}

	@Override
	public int removeOwner(String id) {
		 int i = 0;
		 Connection con = MyConnection.getConnection();
		 StringBuffer sql = new StringBuffer();
		 sql.append("update owner_info set o_state=?");
		 sql.append("where o_id = ? ");
		 try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			pst.setString(1,"离开");
			pst.setString(2,id);
			i = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return i;
	}

	@Override
	public boolean insertManyOwner(List<Owner> list) {
		 Connection con = MyConnection.getConnection();
		 try {
			con.setAutoCommit(false);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 StringBuffer sql = new StringBuffer();
		 sql.append("insert into owner_info (o_name,o_id,o_phone,o_state,o_remark) ");
		 sql.append("values (?,?,?,?,?) ");
		 try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			 for (Owner o : list) {
					pst.setString(1, o.getO_name());
					pst.setString(2, o.getO_id());
					pst.setString(3, o.getO_phone());
					pst.setString(4, "居住");
					pst.setString(5, o.getO_remark());
					pst.addBatch();
				} 
		     pst.executeBatch();
		     con.commit();
			 return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return false;
	}

	@Override
	public List selectO_ownerId() {
		List list = new ArrayList();
		Connection con = MyConnection.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("select  distinct O_ownerId from owner_info ");
		try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			ResultSet rs=  pst.executeQuery();
			while(rs.next())
			{
				list.add(rs.getInt("O_ownerId"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
