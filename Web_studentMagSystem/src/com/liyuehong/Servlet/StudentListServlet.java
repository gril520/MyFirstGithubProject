package com.liyuehong.Servlet;


import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liyuehong.Dao.StudentDao;
import com.liyuehong.Domian.PageBean;
import com.liyuehong.Domian.Student;
import com.liyuehong.Utils.DbUtil;
import com.liyuehong.Utils.JsonUtil;
import com.liyuehong.Utils.ResponseUtil;
import com.liyuehong.Utils.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class StudentListServlet extends HttpServlet {
	DbUtil dbUtil = new DbUtil();
	StudentDao studentDao = new StudentDao();
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String stuNo = request.getParameter("stuNo");
		String stuName = request.getParameter("stuName");
		String sex = request.getParameter("sex");
		String bbirthday = request.getParameter("bbirthday");
		String ebirthday = request.getParameter("ebirthday");
		String gradeId = request.getParameter("gradeId");
		Student student = new Student();
		if(stuNo!=null){
			student.setStuName(stuName);
			student.setSex(sex);
			student.setStuNo(stuNo);
			if(StringUtil.isNotEmpty(gradeId)){
				student.setGradeId(Integer.parseInt(gradeId));
			}
		}
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		PageBean pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		
		Connection con = null;
		try{
			con = dbUtil.getCon();
			JSONObject result = new JSONObject();
			ResultSet rs = studentDao.getStudentList(con, pageBean,student,bbirthday,ebirthday);
			JSONArray jsonArray = JsonUtil.resultToArray(rs);
			int total = studentDao.getCount(con,student,bbirthday,ebirthday);
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