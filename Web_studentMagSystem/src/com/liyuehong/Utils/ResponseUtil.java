package com.liyuehong.Utils;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class ResponseUtil {
	//������ҳ�洫������
	public static void write(HttpServletResponse response,Object jsonObject)throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println(jsonObject.toString());
		out.flush();
		out.close();
	}
}
