package com.liyuehong.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.liyuehong.Domian.User;

public class IndexDao {
	/**
	 * µÇÂ¼ÑéÖ¤
	 * @param con
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public User login(Connection con,User user){
		User resultUser = null;
		String sql = "select * from t_user where userName=? and password=?";
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1,user.getUsername());
			statement.setString(2, user.getPassword());
			ResultSet rs = statement.executeQuery();
			if(rs.next()){
				resultUser = new User();
				resultUser.setUsername(rs.getString("userName"));
				resultUser.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultUser;
		
	}

}
