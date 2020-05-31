package com.liyuehong.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.liyuehong.Domian.Grade;
import com.liyuehong.Domian.PageBean;
import com.liyuehong.Utils.StringUtil;

/*
		 * @ClassName:GradeDao
		 * @Description:班级操作，添加，修改，删除，搜索
		 * @author:liyuehong
		 * @date:2018年5月5日
		 * */
public class GradeDao {
	//将数据数据库取出，显示数据
	//select * from t_grade	limit  start,size;
	public ResultSet gradeList(Connection con,PageBean pageBean,Grade grade) throws SQLException{
		StringBuffer sb = new StringBuffer("select * from t_grade");
		if(grade!=null&&StringUtil.isNotEmpty(grade.getGradeName())){
			sb.append(" and gradeName like '%"+grade.getGradeName()+"%'");
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement preState = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet rs = preState.executeQuery();
		return rs; 
	 }
	//显示总记录数
	public int getCount(Connection con,Grade grade) throws SQLException{
		StringBuffer sb = new StringBuffer("select count(*) as total from t_grade");
		if(grade!=null&&StringUtil.isNotEmpty(grade.getGradeName())){
			sb.append(" and gradeName like '%"+grade.getGradeName()+"%'");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
	//实现删除操作
	public int gradeDelete(Connection con,String delIds) throws SQLException{
		String sql="delete from t_grade where id in("+delIds+")";
		PreparedStatement pstmt = con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}
	//实现添加操作
	public int gradeAdd(Connection con,Grade grade) throws SQLException{
		String sql = "insert into t_grade values(null,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, grade.getGradeName());
		pstmt.setString(2, grade.getGradeDesc());
		return pstmt.executeUpdate();
	}
	//实现修改操作
	public int gradeModify(Connection con,Grade grade) throws SQLException {
		String sql = "update t_grade set gradeName=?,gradeDesc=? where id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, grade.getGradeName());
		pstmt.setString(2, grade.getGradeDesc());
		pstmt.setInt(3, grade.getId());
		return pstmt.executeUpdate();
	}
}
