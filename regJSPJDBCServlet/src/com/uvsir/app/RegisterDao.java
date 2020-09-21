package com.uvsir.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterDao {

	private String dburl="jdbc:mysql://localhost:3306/userdb";
	private String dbuname="root";
	private String dbpassword="root";
	private String dbdriver="com.mysql.cj.jdbc.Driver";
	
	public void loadDriver(String dbDriver) {
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public Connection getConnection() {
		Connection conn=null;
		try {
			conn=DriverManager.getConnection(dburl,dbuname,dbpassword);	
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return conn;
		
	}
	public String insert(Member member) {
	    loadDriver(dbdriver);
		Connection conn=getConnection();
		String result="Result added Successfully";
		String sql="insert into member values(?,?,?,?)";
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
		       ps.setString(1, member.getUname());
		       ps.setString(2, member.getPassword());
		       ps.setString(3, member.getEmail());
		       ps.setString(4, member.getPhone());
		       ps.executeUpdate();
		} catch (SQLException e) {
		
			e.printStackTrace();
			result="data not addedd";
		}
		return result;
	}
	
	
}
