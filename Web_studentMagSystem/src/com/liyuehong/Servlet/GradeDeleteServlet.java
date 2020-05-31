package com.liyuehong.Servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import com.liyuehong.Dao.GradeDao;
import com.liyuehong.Utils.DbUtil;
import com.liyuehong.Utils.ResponseUtil;
import com.mysql.jdbc.EscapeTokenizer;

import net.sf.json.JSONObject;


public class GradeDeleteServlet extends HttpServlet {
	DbUtil dbUtil = new DbUtil();
	GradeDao gradeDao = new GradeDao();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String delIds = request.getParameter("delIds");
		Connection con = null;
		try{
			con = dbUtil.getCon();
			JSONObject result = new JSONObject();
			int deleteNums = gradeDao.gradeDelete(con, delIds);
			if(deleteNums>0){
				result.put("success", "true");
				result.put("deleteNums", deleteNums);
			}else{
				result.put("errorMsg", "É¾³ýÊ§°Ü");
			}
			ResponseUtil.write(response, result);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				dbUtil.close(con);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}