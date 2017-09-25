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
<title>SelectEstatePerson</title>
<script>
$(function(){
	//配置提交按钮
	$('#ename').textbox({    
	       
	    iconCls:'icon-man', 
	    iconAlign:'left'       
	});
	$('#ephone').textbox({    
	        
	    iconCls:'icon-man', 
	    iconAlign:'left'       
	});
	$('#eid').textbox({    
	       
	    iconCls:'icon-man', 
	    iconAlign:'left'       
	});
	$('#estarttime').textbox({    
	       	    
	    iconAlign:'left'       
	});
// 	$('#eendtime').textbox({    
// 	    iconAlign:'left'       
// 	});
	//配置按钮
	$("#submit").linkbutton({
		iconCls: 'icon-search',
	    onClick:function(){
	    	$('#form').form('submit');
	    	      
	    }
	});
	//配置form表单
	$("#form").form({
		url:'SubmitEstatePerson',     
	    success:function(data){
	    	var data = eval('(' + data + ')');
	    	if(data.success==true){
// 	    		$.messager.alert('消息提示',data.msg,'info',function(){
// 		    	});
	    		//配置datagrid
	    		$('#dg').datagrid({    
	    		    url:'SelectEstatePerson', 
	    		    rownumbers:true,
	    		    fitColumns:true,
	    		    pageSize:10,
	    		    pageList:[10,20,30,40,50,60,70,80,90,100],
	    		    pagination:true,
	    		    columns:[[
						{field:'ck',title:'复选',checkbox:true,width:200},
						{field:'eno',title:'编号',hidden:false,width:100},
	    		        {field:'ename',title:'姓名',width:100},    
	    		        {field:'ephone',title:'联系方式',width:100},    
	    		        {field:'eid',title:'身份证号',width:100},
	    		        {field:'currentTime',title:'入职时间',width:100,align:'right'}, 
	    		    ]] ,
	    		    toolbar: [{
	    				iconCls: 'icon-add',
	    				text:'添加',
	    				handler: function(){
	    					$('#win1').window('open');
	    				}
	    			},'-',{
	    				iconCls: 'icon-edit',
	    				text:'修改',
	    				handler: function(){
	    					var row  = $('#dg').datagrid('getSelected');
	    					console.log(row)
	    						if(row!=null){
	    							//$("#ename2").textbox("setValue", row.ename);
	    							//$("#ename2").val(row.ename);
	    							$('#win2').window('open');
	    							$('#form2').form('load',{
	    								eno2:row.eno,
	    								uname2:row.ename,
	    								uphone2:row.ephone,
	    								uid2:row.eid,
	    							});


	    						}else{
	    							$.messager.alert('消息提示','请选择修改行！','info',function(){
	    			    				//$('#form1').form('clear');
	    			    			});
	    						}
	    					}
	    			},'-',{
	    				iconCls: 'icon-remove',
	    				text:'移除',
	    				handler: function(){
	    					$('#win3').window('open');
	    				}
	    			}
	    			]
	    		});
	    		$("#dg").datagrid("reload");
	    		//$('#parent').combobox('reload','ComboxParent');
	    	}else{
	    		$.messager.alert('消息提示',data.msg,'info',function(){
	    			//$('#form2').form('clear');
    			});
	    	}	
	    }    
	});
//---------------------------------------------------添加
	
	//配置登录窗口
	$('#win1').window({    
	    width:400,    
	    height:300,    
	    title:'添加窗口',
	    minimizable:false,
	    maximizable:false,
	    draggable:true,
	});
	$('#win1').window('close');
	//配置登录文本框
	$('#uname1').textbox({    
	    //buttonText:'用户名',    
	    iconCls:'icon-man', 
	    iconAlign:'right',
	    prompt:'输入用户名',
	    width:'180',
	    height:'30',
	});
	$('#uphone1').textbox({    
	    //buttonText:'用户名',    
	    iconAlign:'right',
	    prompt:'输入联系方式',
	    width:'180',
	    height:'30',
	});
	$('#uid1').textbox({    
	    //buttonText:'用户名',    
	    iconAlign:'right',
	    prompt:'输入身份证号',
	    width:'180',
	    height:'30',
	});
	//配置提交按钮
	$('#submit1').linkbutton({    
	    iconCls: 'icon-ok',
	    onClick:function(){
	    	//表单内容提交
	    	$('#form1').form('submit'); 
	    }
	});
	$('#exit1').linkbutton({    
	    iconCls: 'icon-cancel',
	    onClick:function(){
	    	$('#win1').window('close');
	    }
	});
	//配置表单，不是提交
	$('#form1').form({    
	    url:'AddEstatePerson',    
	    onSubmit: function(){    
	    	 
	    },    
	    success:function(data){    
	    	var data = eval('(' + data + ')');
	    	if(data.success==true){
	    		$.messager.alert('消息提示',data.msg,'info',function(){
	    			$('#win1').window('close');
	    			$("#dg").datagrid("reload");
	    			
    			});
	    	}else{
	    		$.messager.alert('消息提示',data.msg,'info',function(){
    				
    				
    			});
	    	}
	    }    
	});
	//---------------------------------------------------修改
	//配置登录窗口
	$('#win2').window({    
	    width:400,    
	    height:300,    
	    title:'修改窗口',
	    minimizable:false,
	    maximizable:false,
	    draggable:true,
	});
	$('#win2').window('close');
	//配置登录文本框
	$('#eno2').textbox({    
	    //buttonText:'用户名',    
	    iconCls:'icon-man', 
	    iconAlign:'right',
	    disable:true,
	    editable:false,
	    prompt:'输入用户名',
	    width:'180',
	    height:'30',
	});
	$('#uname2').textbox({    
	    //buttonText:'用户名',    
	    iconCls:'icon-man', 
	    iconAlign:'right',
	    prompt:'输入用户名',
	    width:'180',
	    height:'30',
	});
	$('#uphone2').textbox({    
	    //buttonText:'用户名',    
	    iconAlign:'right',
	    prompt:'输入联系方式',
	    width:'180',
	    height:'30',
	});
	$('#uid2').textbox({    
	    //buttonText:'用户名',    
	    iconAlign:'right',
	    prompt:'输入身份证号',
	    width:'180',
	    height:'30',
	});
	$('estarttime2').textbox({    
	    //buttonText:'用户名',    
	    iconAlign:'right',
	    disable:true,
	    width:'180',
	    height:'30',
	});
	//配置提交按钮
	$('#submit2').linkbutton({    
	    iconCls: 'icon-ok',
	    onClick:function(){
	    	//表单内容提交
	    	$('#form2').form('submit'); 
	    	$('#win2').window('close');
	    }
	});
	$('#exit2').linkbutton({    
	    iconCls: 'icon-cancel',
	    onClick:function(){
	    	$('#win2').window('close');
	    }
	});
	//配置表单，不是提交
	$('#form2').form({    
	    url:'UpdateEstatePerson',    
	    onSubmit: function(){    
	    	 
	    },    
	    success:function(data){    
	    	var data = eval('(' + data + ')');
	    	if(data.success==true){
	    		$.messager.alert('消息提示',data.msg,'info',function(){
	    			$('#win2').window('close');
	    			$("#dg").datagrid("reload");
	    			
    			});
	    	}else{
	    		$.messager.alert('消息提示',data.msg,'info',function(){
    				
    				
    			});
	    	}
	    }    
	});
	//--------------------------删除
	//配置登录窗口
	$('#win3').window({    
	    width:200,    
	    height:120,    
	    title:'确认窗口',
	    minimizable:false,
	    maximizable:false,
	    draggable:true,
	});
	$('#win3').window('close');
	//配置提交按钮
	$('#submit3').linkbutton({    
	    iconCls: 'icon-ok',
	    onClick:function(){
	    	
	    	//var t1 = window.setTimeout(hello,1000); 
	    	//var t2 = window.setTimeout("TimeToDelete()",10000);//使用字符串执行方法 
	    	var t1 = window.setTimeout(TimeToDelete,5000); 
			var t2 = window.setTimeout("TimeToDelete()",5000);
			$('#win3').window('close');
			function TimeToDelete(){
				window.clearTimeout(t1);//去掉定时器 
				var rows  = $('#dg').datagrid('getSelections');
				//alert(rows.length);
				if(rows.length!=0){
					var enos = [];
					for (var i = 0; i < rows.length; i++) {
						enos.push(rows[i].eno);
					}
					$.ajax({
						url:'DelEstatePerson',
						type:'post',
						dataType:'text',
						data:{'enos':enos},
						success:function(data){
							var data = eval('(' + data + ')');
					    	if(data.success==true){
					    		$.messager.alert('消息提示',data.msg,'info',function(){
						    		$('#win3').window('close');
						    		$('#dg').datagrid('reload');
						    	});
					    	}else{
					    		$.messager.alert('消息提示',data.msg,'info',function(){
					    			$('#win3').window('close');
				    			});
					    	}
						}
					});
				}else{
					$.messager.alert('消息提示','请选择删除行！','info',function(){
			    		$('#dg').datagrid('reload');
			    	});
				}
				$('#win3').window('close');
			}
				
			}

	});
	$('#exit3').linkbutton({    
	    iconCls: 'icon-cancel',
	    onClick:function(){
	    	$('#win3').window('close');
	    }
	});
});


</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',title:'查询条件',split:true" style="height:100px;">
		<div style="margin-left: 100px;margin-top: 16px">
			<form id="form" method="post">
				姓名：<input id="ename" name="ename" type="text" style="width:160px">
				联系方式：<input id="ephone" name="ephone" type="text" style="width:160px">
				身份证号：<input id="eid" name="eid" type="text" style="width:160px">
				入职时间：<input id="estarttime" name="estarttime" class="easyui-datebox"  type="text" style="width:160px">
				<a id="submit" href="#">查询</a>
			</form>
		</div>
	</div>   
   
    <div data-options="region:'center',title:'查询结果'" style="padding:5px;background:#eee;">
    	<table id="dg"></table>  
    </div>
    <!-- 弹出窗口 -->
    <div id="win1">
		<form id="form1" method="post">  
    		<div style="margin-left: 70px;margin-top: 50px;">    
        		姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：<input id="uname1" name="uname1" type="text" data-options="required:true" style="width:250px">&nbsp;
				<br><br>
				联系方式：<input id="uphone1" name="uphone1" type="text" data-options="required:true" style="width:250px">
				<br><br>
				身份证号：<input id="uid1" name="uid1"  type="text" data-options="required:true" style="width:250px">
				<br><br>
				<a id="submit1" href="#">提交</a>&nbsp;&nbsp;<a id="exit1" href="#">取消</a>
    		</div>       
	    </form>  
	</div>
    <div id="win2">
		<form id="form2" method="post">  
    		<div style="margin-left: 70px;margin-top: 50px;">  
    			编&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：<input id="eno2" name="eno2" type="text" data-options="required:true" style="width:250px">
				<br><br>  
        		姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：<input id="uname2" name="uname2" type="text" data-options="required:true" style="width:250px">&nbsp;
				<br><br>
				联系方式：<input id="uphone2" name="uphone2" type="text" data-options="required:true" style="width:250px">
				<br><br>
				身份证号：<input id="uid2" name="uid2"  type="text" data-options="required:true" style="width:250px">
				<br><br>
				<a id="submit2" href="#">提交</a>&nbsp;&nbsp;<a id="exit2" href="#">取消</a>
    		</div>       
	    </form>  
	</div>
	<div id="win3" >
		<div id="modifyDiv1" style="margin-left: 20px;margin-top: 10px">
  		<p>确认删除选中行？</p>
  		<a id="submit3" href="#">确认</a>&nbsp;&nbsp;<a id="exit3" href="#">取消</a>
		</div>
	</div>   	
</body>
</html>