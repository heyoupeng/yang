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
	$(function() {
		//登录窗口
		$('#updatePasswordWindow').window({
			width : 600,
			height : 400,
			title : '修改密码',
			closable : false,
			collapsible : false
		});
		$('#oldPassword').textbox({
			iconCls : 'icon-man',
			iconAlign : 'left',
			prompt : '旧密码',
			height : 40
		})
		$('#newPassword').textbox({
			iconCls : 'icon-man',
			iconAlign : 'left',
			prompt : '新密码',
			height : 40
		})
		$('#surePassword').textbox({
			iconCls : 'icon-man',
			iconAlign : 'left',
			prompt : '确认新密码',
			height : 40
		})


		//配置表单（无，直接通过异步请求进行判断）
		$('#updateForm').form({
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
				var newPassword=$('#newPassword').textbox('getText');
				var surePassword=$('#surePassword').textbox('getText');
				if(newPassword!=surePassword){
					$.messager.alert('提示', '确认新密码错误');
				}else{
					var oldPassword=$('#oldPassword').textbox('getText');
					var md5OldPassword=md5(oldPassword);
					var md5NewPassword = md5(newPassword);
					$.ajax({
						url : 'updatePassword',
						type : 'post',
						data : {
							'oldPassword' : md5OldPassword,
							'newPassword' : md5NewPassword,
						},
						dataType : 'json',
						success : function(data) {
							console.log(data.state);
							if (data.state == "200") {
								$.messager.alert('提示', '修改成功');
							} else if (data.state == "400") {
								$.messager.alert('提示', '未知错误导致修改失败');
							} else if (data.state == "401") {
								$.messager.alert('提示', '帐号已退出或未登录');
							} else if(data.state=="402"){
								$.messager.alert('提示', '旧密码错误');
							} else if(data.state=="403"){
								$.messager.alert('提示', '新密码不能为空');
							} else {
								$.messager.alert('提示', '未知错误');
							}
						}
					});//end ajax
				}
			}//end onClick
		});
	});
</script>
<body>
	<div id="updatePasswordWindow">
		<form id="updateForm" method="post">
			<div style="margin-left: 120px; margin-top: 100px">
				<h3>修改密码</h3>
				<br>
				旧密码：
				<input id="oldPassword" name="oldPassword" type="password" style="width: 300px;"> 
				<br> <br> 
				新密码：
				<input id="newPassword" name="newPassword" type="password" style="width: 300px">
				<br> <br> 
				确认新密码：
				<input id="surePassword" name="surePassword" type="password" style="width: 300px"> 
				<br> <br> 
				<a id="login" href="#">确认修改</a> <br>
			</div>
		</form>
	</div>
</body>
</html>