package com.liyuehong.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
	private String user = "root";
	private String password = "123";
	private String url="jdbc:mysql://localhost:3306/stumagsystem";
	private String className="com.mysql.jdbc.Driver";//加载jdbc-mysql驱动
	
	
	public Connection getCon(){
		Connection con = null;
		try {
			Class.forName(className);
			con = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public void close(Connection con){
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args){
		DbUtil dbUtil = new DbUtil();
		System.out.println(dbUtil.getCon());
		System.out.println("数据库连接成功!");
	}
}
