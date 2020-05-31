package com.liyuehong.Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liyuehong.Dao.StudentDao;
import com.liyuehong.Domian.Student;
import com.liyuehong.Utils.DateUtil;
import com.liyuehong.Utils.DbUtil;
import com.liyuehong.Utils.StringUtil;

import net.sf.json.JSONObject;

public class StudentSaveServlet extends HttpServlet {
		DbUtil dbUtil = new DbUtil();
		StudentDao studentDao = new StudentDao();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String stuName = request.getParameter("stuName");
		String stuDesc = request.getParameter("stuDesc");
		String stuNo = request.getParameter("stuNo");
		String sex = request.getParameter("sex");
		String birthday = request.getParameter("birthday");
		String email = request.getParameter("email");
		String gradeId = request.getParameter("gradeId");
		String stuId = request.getParameter("stuId");
		Student student = null;
		try {
			student = new Student(stuNo, stuName, sex, DateUtil.formatString(birthday, "yyyy-MM-dd"),Integer.parseInt(gradeId),email, stuDesc);
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		if(StringUtil.isNotEmpty(stuId)){
			student.setStuId(Integer.parseInt(stuId));
		}
		Connection con = null;
		try{
			con = dbUtil.getCon();
			JSONObject result = new JSONObject();
			int AddNum = 0;
			if(StringUtil.isNotEmpty(stuId)){
				AddNum = studentDao.studentModify(con, student);
			}else{
				AddNum = studentDao.studentSave(con, student);
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