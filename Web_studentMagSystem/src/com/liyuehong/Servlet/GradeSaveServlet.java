package com.liyuehong.Servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liyuehong.Dao.GradeDao;
import com.liyuehong.Domian.Grade;
import com.liyuehong.Utils.DbUtil;
import com.liyuehong.Utils.StringUtil;

import net.sf.json.JSONObject;

public class GradeSaveServlet extends HttpServlet {
		DbUtil dbUtil = new DbUtil();
		GradeDao gradeDao = new GradeDao();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String gradeName = request.getParameter("gradeName");
		String gradeDesc = request.getParameter("gradeDesc");
		String id = request.getParameter("id");
		Grade grade = new Grade(gradeName, gradeDesc);
		if(StringUtil.isNotEmpty(id)){
			grade.setId(Integer.parseInt(id));
		}
		Connection con = null;
		try{
			con = dbUtil.getCon();
			JSONObject result = new JSONObject();
			int AddNum = 0;
			if(StringUtil.isNotEmpty(id)){
				AddNum = gradeDao.gradeModify(con, grade);
			}else{
				AddNum = gradeDao.gradeAdd(con, grade);
			}
			if(AddNum>0){
				result.put("success", "true");
			}else{
				result.put("success", "false");
				result.put("errorMsg","±£¥Ê ß∞‹");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}