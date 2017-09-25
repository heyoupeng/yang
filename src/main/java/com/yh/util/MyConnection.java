package com.yh.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
	private final static String DBURL="jdbc:mysql://172.18.23.6:3306/wy?useUnicode=true&characterEncoding=utf8";
	private final static String USERNAME="root";
	private final static String PASSWORD="123";
	private final static String DBDRIVER="com.mysql.jdbc.Driver";
	private static Connection connection=null;
	private MyConnection(){};
	public static Connection getConnection(){
		if(connection!=null){
			return connection;
		}
		try{
			Class.forName(DBDRIVER);
			connection=DriverManager.getConnection(DBURL,USERNAME,PASSWORD);
			return connection;
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
}
