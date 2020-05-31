<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>学生信息管理系统</title>
<%
	if(session.getAttribute("currentUser")==null){
		response.sendRedirect("index.jsp");
		return;
	}
%>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	$(function () {
		//数据
		var treeData=[{
			  text:"根",
			  children:[{
					text:"班级信息管理",
					attributes:{
						url:"gradeInfoManagement.jsp"
					}
				},{
					text:"学生信息管理",
					attributes:{
						url:"studentInfoManagement.jsp"
					}
			   }]
		  }];
		//实力化树菜单
		$("#tree").tree({
			data:treeData,
			lines:true,
			//打开菜单
			onClick:function(node){
				if(node.attributes){
					openTab(node.text,node.attributes.url);
				}
			}
		});
		//新增tab
		function openTab(text,url) {
			if($("#tabs").tabs('exists',text)){
				$("#tabs").tabs('select',text);
			}else{
				var content="<iframe frameborder='0' scrolling='auto'  style='height:100%;width:100%' src="+url+"></iframe>"
				$("#tabs").tabs('add',{
					title:text,
					closable:true,
					content:content
				});
			}
		}
	});
</script>
</head>
<body class="easyui-layout">
    <div region="north" style="background-color: #E0EDFF;height: 80px">
    	<div align="left" style="float: left;width: 80%"><img src="images/main.jpg"></div>
    	<div style="padding-top: 50px;padding-right: 20px">当前用户：&nbsp;<font color="red" >${currentUser.username }</font></div>
    </div>
    <div region="center">
    	<div class="easyui-tabs" fit="true" border="false" id="tabs">
    		<div title="首页">
    			<div align="center" style="padding-top: 100px;"><font color="red" size="10">欢迎使用</font></div>
    		</div>
    	</div>
    </div>
    <div region="west" style="width: 150px;" title="导航菜单" split="true">
    	<ul id="tree"></ul>
    </div>
</body>
</html>