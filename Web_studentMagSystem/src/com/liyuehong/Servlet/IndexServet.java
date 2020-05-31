package com.liyuehong.Servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.liyuehong.Dao.IndexDao;
import com.liyuehong.Domian.User;
import com.liyuehong.Utils.DbUtil;
import com.liyuehong.Utils.StringUtil;

public class IndexServet extends HttpServlet {
	IndexDao indexDao = new IndexDao();
	DbUtil dbUtil = new DbUtil();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		request.setAttribute("username", userName);
		request.setAttribute("password", password);
		if(StringUtil.isEmpty(userName)||StringUtil.isEmpty(password)){
			request.setAttribute("error", "用户名或密码为空!");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		User userMain = new User(userName,password);
		Connection con = null;
		try{
			con = dbUtil.getCon();
			User currentUser = indexDao.login(con, userMain);
			if(currentUser==null){
				request.setAttribute("error", "用户名或密码错误!");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}else{
				HttpSession session = request.getSession();
				session.setAttribute("currentUser", currentUser);
				response.sendRedirect("main.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			try {
				dbUtil.close(con);;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}