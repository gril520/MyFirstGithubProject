package com.liyuehong.Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liyuehong.Dao.GradeDao;
import com.liyuehong.Domian.Grade;
import com.liyuehong.Domian.PageBean;
import com.liyuehong.Utils.DbUtil;
import com.liyuehong.Utils.JsonUtil;
import com.liyuehong.Utils.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GradeListServlet extends HttpServlet {
	DbUtil dbUtil = new DbUtil();
	GradeDao gradeDao = new GradeDao();
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		String gradeName = request.getParameter("gradeName");
		if(gradeName==null){
			gradeName="";
		}
		//System.out.println(rows);
		//·â×°grade
		Grade grade = new Grade();
		grade.setGradeName(gradeName);
		//·â×°PageBean
		PageBean pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		/*System.out.println(Integer.parseInt(page));
		System.out.println(Integer.parseInt(rows));
		System.out.println(pageBean.getStart());*/
		Connection con = null;
		try{
			con = dbUtil.getCon();
			JSONObject result = new JSONObject();
			ResultSet rs = gradeDao.gradeList(con, pageBean,grade);
			JSONArray jsonArray = JsonUtil.resultToArray(rs);
			int total = gradeDao.getCount(con,grade);
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.close(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}