package com.db;

import java.sql.*;

public class DBHelper {
	private String dbUrl="jdbc:mysql://1.15.46.56:3306/sushe?useUnicode=true&characterEncoding=utf8";
	private String dbUser="root";
	private String dbPassword="Chen123456...";
	private String jdbcName="com.mysql.cj.jdbc.Driver";

	//连接数据库测试
	public Connection getConn(){
		Connection conn = null;
		try{
			Class.forName(jdbcName);
		}
		catch(Exception e){}
		try{
			conn=DriverManager.getConnection(dbUrl,dbUser,dbPassword);
		}
		catch(SQLException ex){
			System.out.println(ex);
		}
		return conn;
	}

	//    测试
	//
	public static void main(String[] args)
	{
		System.out.println(new DBHelper().getConn());

	}

}
