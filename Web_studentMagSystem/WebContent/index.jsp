<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript">
	function resetValue() {
		document.getElementById("username").value="";
		document.getElementById("password").value="";
	}
</script>
<title>学生信息管理系统登录</title>
</head>
<body style="background-image:url(images/02.jpg); background-size:100% 100%; overflow: hidden;margin: 0px;padding: 0px">
	<div  align="center"  style="padding-top: 240px;padding-bottom: 310px">
		<form action="IndexServet" method="post">
			<table height="150px" width="240px">
				<tr style="height: 25%">
					<td>用户名:</td>
					<td><input type="text" value="${username }"  name="username" id="username"></td>
				</tr>
				<tr style="height: 25%">
					<td>密码:</td>
					<td><input type="password" value="${password }"  name="password" id="password"></td>
				</tr style="height: 25%">
				<tr align="center">
					<td style="padding-left: 10px"><input type="submit" value="登入"></td>
					<td style="padding-right: 40px;padding-left: 20px"><input type="button" value="重置" onclick="resetValue()"></td>
				</tr>
				<tr style="height: 25%">
					<td colspan="3">
					<font color="red">${error }</font>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>