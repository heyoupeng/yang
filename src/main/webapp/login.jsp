<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 

"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 引入主题样式 -->
<link href="easyui/themes/gray/easyui.css" rel="stylesheet">
<!-- 引入图标的样式 -->
<link href="easyui/themes/icon.css" rel="stylesheet">
<script src="http://cdn.bootcss.com/blueimp-md5/1.1.0/js/md5.js"></script>
<!-- 先引入jquery -->
<!-- 引入easyui -->
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
</head>
<script type="text/javascript">
	if (window != top)
		top.location.href = location.href;
	$(function() {
		//登录窗口
		$('#loginWindow').window({
			width : 600,
			height : 400,
			title : '登录',
			closable : false,
			collapsible : false
		});
		//用户名和密码
		$('#username').textbox({
			iconCls : 'icon-man',
			iconAlign : 'left',
			prompt : '用户名',
			height : 40
		})

		$('#password').textbox({
			iconCls : 'icon-lock',
			iconAlign : 'left',
			prompt : '密码',
			height : 40
		})

		//角色
		$('#role').combobox({
			url : 'getAllRoles',
			valueField : 'id',
			textField : 'text',
			editable : false,
			height : 30
		});
		//配置表单（无，直接通过异步请求进行判断）
		$('#loginForm').form({
			url : 'login',
			onSubmit : function() {

			},
			success : function(data) {

			}
		});
		//配置提交按钮
		$('#login').linkbutton({
			iconCls : 'icon-ok',
			onClick : function() {
				var text = $('#password').textbox('getText');
				var md5Password = md5(text);
				$.ajax({
					url : 'login',
					type : 'post',
					data : {
						'rid' : $('#role').combo('getValue'),
						'username' : $('#username').textbox('getText'),
						'password' : md5Password
					},
					dataType : 'json',
					success : function(data) {
						console.log(data.state);
						if (data.state == "200") {
							location.href = 'main.jsp';
						} else if (data.state == "400") {
							$.messager.alert('提示', '帐号,密码或角色错误');
						} else if (data.state == "401") {
							$.messager.alert('提示', '帐号已在别处登录，请勿重复登录');
						} else if(data.state=="402"){
							$.messager.alert('提示', '错误次数过多，请在'+data.unLocktime+'后再试');
						} else {
							$.messager.alert('提示', '未知错误');
						}
					}
				});//end ajax
			}//end onClick
		});
	});
</script>
<body>
	<div id="loginWindow">
		<!--登陆入口  -->
		<form id="loginForm" method="post">
			<div style="margin-left: 120px; margin-top: 100px">
				<h3>登录账户</h3>
				<br> <input id="username" name="username" type="text"
					style="width: 300px;"> <br> <br> <input
					id="password" name="password" type="password" style="width: 300px">
				<br> <br> <input id="role" name="rid"
					class="easyui-combobox"> <br> <br> <a id="login"
					href="#">提交</a> <br>
			</div>
		</form>
	</div>
</body>
</html>