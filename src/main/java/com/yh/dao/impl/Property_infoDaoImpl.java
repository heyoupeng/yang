package com.yh.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yh.dao.Property_infoDao;
import com.yh.model.Owner;
import com.yh.model.Property;
import com.yh.util.MyConnection;

public class Property_infoDaoImpl implements Property_infoDao{

	@Override
	public int getCount(int buildNo,int unitNo,int floorNo,int roomNo) {
		int i = 0;
		HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>() ;
		int j = 0;
		 Connection con = MyConnection.getConnection();
		 StringBuffer sql = new StringBuffer();
		 sql.append("select count(*) as count from property_info ");
	     if(buildNo!=0||unitNo!=0||floorNo!=0||roomNo!=0)
	     {
	    	 sql.append("where ");
	    	 if(buildNo!=0)
	    	 {
	    		 if(j==0)
	    		 {
	    			 sql.append("P_building = ? ");
	    		 }
	    		 else
	    		 {
	    		 sql.append("and P_building = ? ");
	    		 }
	    		 j++;
	    		 hm.put(j, buildNo);
	    	 }
	    	 if(unitNo!=0)
	    	 { 
	    		 if(j==0)
	    		 {
	    		 sql.append("P_unit = ? ");
	    		 }
	    		 else
	    		 {
	    			 sql.append("and P_unit = ? ");
	    		 }
	    		 j++;
	    		 hm.put(j, unitNo);
	    	 }
	    	 if(floorNo!=0)
	    	 {
	    		 if(j==0)
	    		 {
	    		 sql.append("P_floor = ? ");
	    		 }
	    		 else
	    		 {
	    			 sql.append("and P_floor = ? ");
	    		 }
	    		 j++;
	    		 hm.put(j, floorNo);
	    	 }
	    	 if(roomNo!=0)
	    	 {
	    		 if(j==0)
	    		 {
	    		 sql.append("P_room = ? ");
	    		 }
	    		 else
	    		 {
	    			 sql.append("and P_room = ? ");
	    		 }
	    		 j++;
	    		 hm.put(j, roomNo);
	    	 }
	     }
		 try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			for (int k = 0; k < j; k++) {
				pst.setInt(k+1,hm.get(k+1));
			}
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
	public List<Property> limitProperty_info(int page, int rows,int buildNo,int unitNo,int floorNo,int roomNo) {
		 List<Property> list = new ArrayList<Property>();
		 HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>() ;
			int j = 0;
		 Connection con = MyConnection.getConnection();
		 StringBuffer sql = new StringBuffer();
		 sql.append("select P_building,P_unit,P_floor,P_room,property_info.O_ownerId,O_name,P_area,P_remark  ");
		 sql.append("from owner_info right JOIN property_info  on property_info.O_ownerId = owner_info.O_ownerId where 1=1  ");
		 if(buildNo!=0||unitNo!=0||floorNo!=0||roomNo!=0)
	     {
	    	 if(buildNo!=0)
	    	 {
	    		
	    		 sql.append("and P_building = ? ");
	    		 j++;
	    		 hm.put(j, buildNo);
	    	 }
	    	 if(unitNo!=0)
	    	 { 
	    			 sql.append("and P_unit = ? ");
	    		 j++;
	    		 hm.put(j, unitNo);
	    	 }
	    	 if(floorNo!=0)
	    	 {
	    			 sql.append("and P_floor = ? ");
	    		 j++;
	    		 hm.put(j, floorNo);
	    	 }
	    	 if(roomNo!=0)
	    	 {
	    			 sql.append("and P_room = ? ");
	    		 j++;
	    		 hm.put(j, roomNo);
	    	 }
	     }
		 sql.append("limit ?,? ");
		 try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			for (int k = 0; k < j; k++) {
				pst.setInt(k+1,hm.get(k+1));
			}
			pst.setInt(1+j, page);
			pst.setInt(2+j, rows);
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				Property p = new Property();
				p.setP_building(rs.getInt("P_building"));
				p.setP_unit(rs.getInt("P_unit"));
				p.setP_floor(rs.getInt("P_floor"));
				p.setP_room(rs.getInt("P_room"));
				p.setP_ownerId(rs.getInt("O_ownerId"));
				p.setP_ownerName(rs.getString("O_name"));
				p.setP_area(rs.getDouble("P_area"));
				p.setP_remark(rs.getString("P_remark"));
				list.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return list;
	}

	@Override
	public List selectP_build() {
		List list = new ArrayList();
		Connection con = MyConnection.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("select  distinct P_building from property_info ");
		try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			ResultSet rs=  pst.executeQuery();
			while(rs.next())
			{
				list.add(rs.getInt("P_building"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List selectP_unit() {
		List list = new ArrayList();
		Connection con = MyConnection.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("select  distinct P_unit from property_info ");
		try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			ResultSet rs=  pst.executeQuery();
			while(rs.next())
			{
				list.add(rs.getInt("P_unit"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List selectP_floor() {
		List list = new ArrayList();
		Connection con = MyConnection.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("select  distinct P_floor from property_info ");
		try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			ResultSet rs=  pst.executeQuery();
			while(rs.next())
			{
				list.add(rs.getInt("P_floor"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List selectP_room() {
		List list = new ArrayList();
		Connection con = MyConnection.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("select  distinct P_room from property_info ");
		try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			ResultSet rs=  pst.executeQuery();
			while(rs.next())
			{
				list.add(rs.getInt("P_room"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int insertProperty_info(int build, int unit, int floor, int room, double area, int ownerId, String remark) {
		 int i = 0;
		 Connection con = MyConnection.getConnection();
		 StringBuffer sql = new StringBuffer();
		 StringBuffer sql1 = new StringBuffer();
		 sql.append("insert into Property_info (P_building,P_unit,P_floor,P_room,O_ownerId,P_area,P_remark) ");
		 sql.append("values (?,?,?,?,?,?,?)");
		 sql1.append("insert into Property_info (P_building,P_unit,P_floor,P_room,P_area,P_remark) ");
		 sql1.append("values (?,?,?,?,?,?)");
		 try {
			 if(ownerId==0)
			 {
				 PreparedStatement pst = con.prepareStatement(sql1.toString());
					pst.setInt(1, build);
					pst.setInt(2, unit);
					pst.setInt(3, floor);
					pst.setInt(4, room);
					pst.setDouble(5, area);
					pst.setString(6, remark);
					i = pst.executeUpdate();
			 }
			 else
			 {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			pst.setInt(1, build);
			pst.setInt(2, unit);
			pst.setInt(3, floor);
			pst.setInt(4, room);
			pst.setDouble(6, area);
			pst.setInt(5,ownerId);
			pst.setString(7, remark);
			i = pst.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return i ;
	}

	@Override
	public int updateProperty_info(int build, int unit, int floor, int room, double area, int ownerId, String remark) {
		 int i = 0;
		 Connection con = MyConnection.getConnection();
		 StringBuffer sql = new StringBuffer();
		 sql.append("update Property_info set O_ownerId=?,P_area=?,P_remark=?  ");
		 sql.append("where P_building =? and P_unit=? and P_floor=? and P_room=? ");
		 try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			pst.setInt(4, build);
			pst.setInt(5, unit);
			pst.setInt(6, floor);
			pst.setInt(7, room);
			pst.setDouble(2, area);
			pst.setInt(1,ownerId);
			pst.setString(3, remark);
			i = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return i ;
	}

	@Override
	public int deleteProperty_info(int build, int unit, int floor, int room) {
		 int i = 0;
		 Connection con = MyConnection.getConnection();
		 StringBuffer sql = new StringBuffer();
		 sql.append("delete from  Property_info where ");
		 sql.append("P_building =? and P_unit=? and P_floor=? and P_room=?");
		 try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			pst.setInt(1, build);
			pst.setInt(2, unit);
			pst.setInt(3, floor);
			pst.setInt(4, room);
			i = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return i ;
	}

}
