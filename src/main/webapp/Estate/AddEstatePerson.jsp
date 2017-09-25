<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 

"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 引入主题样式 -->
<link
	href="${pageContext.request.contextPath}/easyui/themes/gray/easyui.css"
	rel="stylesheet">
<!-- 引入图标的样式 -->
<link href="${pageContext.request.contextPath}/easyui/themes/icon.css"
	rel="stylesheet">
<!-- 先引入jquery -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<!-- 引入easyui -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>
<title>AddEstatePerson</title>
<script>
$(function(){
	//配置登录窗口
	$('#win').window({    
	    width:600,    
	    height:400,    
	    title:'登陆窗口',
	    minimizable:false,
	    maximizable:false,
	    draggable:true,
	});
	//配置登录文本框
	$('#uname').textbox({    
	    //buttonText:'用户名',    
	    iconCls:'icon-man', 
	    iconAlign:'right',
	    prompt:'输入用户名',
	    width:'180',
	    height:'30',
	});
	$('#uphone').textbox({    
	    //buttonText:'用户名',    
	    iconAlign:'right',
	    prompt:'输入联系方式',
	    width:'180',
	    height:'30',
	});
	$('#uid').textbox({    
	    //buttonText:'用户名',    
	    iconAlign:'right',
	    prompt:'输入身份证号',
	    width:'180',
	    height:'30',
	});
	$('#ustarttime').textbox({    
	    //buttonText:'用户名',     
	    iconAlign:'right',
	    prompt:'输入入职时间',
	    width:'180',
	    height:'30',
	});
	$('#uendtime').textbox({    
	    //buttonText:'用户名',    
	    iconAlign:'right',
	    prompt:'输入离职时间',
	    width:'180',
	    height:'30',
	});
	//配置提交按钮
	$('#submit').linkbutton({    
	    iconCls: 'icon-ok',
	    onClick:function(){
	    	//表单内容提交
	    	$('#form').form('submit'); 
	    }
	});
	$('#exit').linkbutton({    
	    iconCls: 'icon-cancel',
	    onClick:function(){
	    	$('#win').window('close');
	    }
	});
	//配置表单，不是提交
	$('#form').form({    
	    url:'AddEstatePerson',    
	    onSubmit: function(){    
	    	 
	    },    
	    success:function(data){    
	    	var data = eval('(' + data + ')');
	    	if(data.success==true){
	    		$.messager.alert('消息提示',data.msg,'info',function(){
    				
    				
    			});
	    	}else{
	    		$.messager.alert('消息提示',data.msg,'info',function(){
    				
    				
    			});
	    	}
	    }    
	});    
// 入职时间：<input id="ustarttime" name="ustarttime" data-options="required:true" type="text" style="width:250px">
// 	<br><br>
// 	当前时间：<input id="currentTime" name="currentTime" type="text" class="easyui-datebox" required="required">
// 	<br><br>
});
</script>
</head>
<body>
	<div id="win">
		<!--登陆入口  --> 
		<form id="form" method="post">  
			<div  style="margin-left: 70px;margin-top: 80px">
				姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：<input id="uname" name="uname" type="text" data-options="required:true" style="width:250px">&nbsp;
				<br><br>
				联系方式：<input id="uphone" name="uphone" type="text" data-options="required:true" style="width:250px">
				<br><br>
				身份证号：<input id="uid" name="uid"  type="text" data-options="required:true" style="width:250px">
				<br><br>
				<a id="submit" href="#">提交</a>&nbsp;&nbsp;<a id="exit" href="#">退出</a>    
			</div>
		</form>
	</div> 
</body>
</html>