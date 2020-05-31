<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>班级信息管理</title>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	var url;
	function searchGrade() {
		$('#dg').datagrid('load',{
			gradeName:$('#s_gradeName').val()
		});
	}
	
	function deleteGrade() {
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要删除的数据！");
			return;
		}
		var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].id);
		}
		//alert(strIds);
		var ids = strIds.join(',');
		$.messager.confirm("系统提示","你确认要删除这<font color=red>"+selectedRows.length+"</font>条数据吗?",
		function(r){
			if(r){
				$.post("${pageContext.request.contextPath }/GradeDeleteServlet",{delIds:ids},function(result){
					if(result.success){
						$.messager.alert("系统提示","您已成功删除<font color=red>"+result.deleteNums+"</font>条数据！");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert('系统提示','<font color=red>'+selectedRows[result.errorIndex].gradeName+'</font>'+result.errorMsg);
					}
				},"json");
			}
		});
	}
	
	function oppenGradeDialog() {
		$("#dlg").dialog("open").dialog("setTitle","添加班级信息");
		url="${pageContext.request.contextPath }/GradeSaveServlet";
	}
	
	function modifyGrade(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var row=selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","编辑班级信息");
		$("#fm").form("load",row);
		url="${pageContext.request.contextPath }/GradeSaveServlet?id="+row.id;
	}
	
	function closeGradeDialog(){
		$("#dlg").dialog("close");
		resetValue();
	}
	
	function resetValue(){
		$("#gradeName").val("");
		$("#gradeDesc").val("");
	}
	
	function saveGrade(){
		$("#fm").form("submit",{
			url:url,
			onSubmit:function(){
				return $(this).form("validate");
			},
			success:function(result){
				if(result.errorMsg){
					$.messager.alert("系统提示",result.errorMsg);
					return;
				}else{
					$.messager.alert("系统提示","保存成功");
					resetValue();
					$("#dlg").dialog("close");
					$("#dg").datagrid("reload");
				}
			}
		});
		
	}
</script>
</head>
<body style="magrn:5px">
	<table id="dg" title="班级信息" class="easyui-datagrid" fitColumns="true" 
	pagination="true" rownumbers="true"  fit="true" url="${pageContext.request.contextPath }/GradeListServlet"  toolbar="#tb"> 
		<thead>
			<tr>
				<th field="cb" checkbox="true" width="25px"></th>
				<th field="id" width="25px">编号</th>
				<th field="gradeName" width="100px">班级名称</th>
				<th field="gradeDesc" width="250px">班级描述</th>
			</tr>
		</thead>
	</table>
	<div id="tb">
		<div>
			<a href="javascript:oppenGradeDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="javascript:modifyGrade()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
			<a href="javascript:deleteGrade()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</div>
		<div>&nbsp;班级名称&nbsp;<input type="text" name="s_gradeName" id="s_gradeName"> 
		 <a href="javascript:searchGrade()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>
	
	<div id="dlg" class="easyui-dialog" style="width: 400px;height: 280px;padding: 10px 20px"
		closed="true" buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table>
				<tr>
					<td>班级名称：</td>
					<td><input type="text" name="gradeName" id="gradeName" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td valign="top">班级描述：</td>
					<td><textarea rows="7" cols="30" name="gradeDesc" id="gradeDesc"></textarea></td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="dlg-buttons">
		<a href="javascript:saveGrade()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeGradeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>