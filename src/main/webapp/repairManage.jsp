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
</head>
<script type="text/javascript">
	$(function() {
		var idReg='/^/'
		$('#repairMessages').datagrid({
			url : 'getRepairs',//创建时访问的url
			pagination : true,//分页
			pageSize : 5,//一页一共有多少条数据
			pageList : [ 3, 4, 5, 6, 7, 8, 9, 10 ],
			rownumbers : true,//展示是当前表格的第几行
			title : '维护人员信息',
			columns : [ [//行中的数据设置（ck为checkbox，以下的数据为json中）
			{
				field : 'ck',
				checkbox : true,
				width : 100
			}, {
				field : 'no',
				title : '编号',
				width : 100,
				hidden : true
			}, {
				field : 'name',
				title : '姓名',
				width : 100
			}, {
				field : 'id',
				title : '身份证'
			}, {
				field : 'phone',
				title : '联系方式',
				width : 100
			}, {
				field : 'startTime',
				title : '入职时间',
                formatter:
                	function(value,row,index){  
                		if(value!=null){
                    		var year=parseInt(value.year)+1900;  
                    		var month=(parseInt(value.month)+1);  
                    		month=month>9?month:('0'+month);  
                    		var date=parseInt(value.date);  
                    		date=date>9?date:('0'+date); 
                    		var time=year+'-'+month+'-'+date;  
                    		return time;
                		}
                	}
			} 
			] ],
			toolbar : [{//配置工具栏
				iconCls : 'icon-add',//图标
				text : '添加', //文字
				handler : function() { //点击事件
					$("#inputKind").text("添加维修人员信息");
					$("#submitButton").linkbutton({
						iconCls : 'icon-ok',
						onClick : function() {
							//缺判断的正则表达式
							$('#inputForm').form('submit');
						}
					});	
					//为提交信息窗口绑定添加事件
					$("#inputForm").form({
						url : 'insertRepair',
						success : function(data) {
							var msg=eval('('+data+')');//将获得的信息变为obj
							if(msg.state=="200"){
								$.messager.alert('提示','添加成功','info',function() {
									$('#inputForm').form('clear');
									$('#insertWindow').window('close');
									$('#repairMessages').datagrid('reload');
								});
							}else if(msg.state=="400"){
								$.messager.alert('提示','添加失败','info',function() {
									$('#inputForm').form('clear');
									$('#insertWindow').window('close');
									$('#repairMessages').datagrid('reload');
								});									
							}else{
								$.messager.alert('提示','未知错误导致添加失败','info',function() {
									$('#inputForm').form('clear');
									$('#insertWindow').window('close');
									$('#repairMessages').datagrid('reload');
								});									
							}				
						}
					});
					$('#insertWindow').window('open');
				}
			},'-',{
				iconCls : 'icon-edit',
				text : '修改',
				handler : function() {
					$("#inputKind").text("修改维修人员信息");
					var rows = $('#repairMessages').datagrid('getSelections');
					if(rows.length==1){
						//为提交信息窗口绑定更新事件
						$("#inputForm").form({
							url : 'updateRepair',
							success : function(data) {
								$.messager.alert('提示','成功','info',function() {
									$('#inputForm').form('clear');
									$('#insertWindow').window('close');
									$('#repairMessages').datagrid('reload');
								});
							}
						});
						$('#insertWindow').window('open');
						$("#submitButton").linkbutton({
							iconCls : 'icon-ok',
							onClick : function() {
								$('#inputForm').form('submit');
							}
						});
						var row = $('#repairMessages').datagrid('getSelected');
						var year=parseInt(row.startTime.year)+1900;  
                		var month=(parseInt(row.startTime.month)+1);  
                		month=month>9?month:('0'+month);  
                		var date=parseInt(row.startTime.date);  
                		date=date>9?date:('0'+date); 
                		var time=year+'-'+month+'-'+date;
						$('#inputStartTime').datebox('setValue',time);
						$('#inputForm').form('load', row);
					}else if(rows.length==0){
						$.messager.alert('警告','未选择修改信息');
					}else{
						$.messager.alert('警告','一次只能修改一条信息','info',function(){
							$('#repairMessages').datagrid('reload');
						});
					}
					
				}
			},'-',{
				iconCls : 'icon-remove',
				text : '删除',
				handler : function() {
					var rows = $('#repairMessages').datagrid('getSelections');
					var no = [];
					for (var i = 0; i < rows.length; i++) {
						no.push(rows[i].no);
					}
					$.messager.confirm('确认','您确认想要删除记录吗？',function(r) {
						if (r) {
							$.ajax({
								url : 'deleteRepairs',
								type : 'post',
								dataType : 'json',
								data : {
									'no' : no
								},
								success:function(data) {
									if(data.state=="200"){
										$('#repairMessages').datagrid('reload');
										$.messager.alert('提示','删除成功');
									}else if(data.state=="400"){
										$.messager.alert('提示','删除失败');
									}
									
								}
							});
						}
					});
				}
			}]
		});

		$("#noSubmitButton").linkbutton({
			iconCls : 'icon-no',
			onClick : function() {
				$('#inputForm').form('clear');
				$('#insertWindow').window('close');
			}
		});
		$('#insertWindow').window({    
		    width:440,    
		    height:300,
		    modal:true,
		    closed:true
		});
		$('#inputName').textbox({
			prompt : '姓名',
			height : 30,
			width  : 250
		})
		$('#inputId').textbox({
			prompt : '身份证',
			height : 30,
			width  : 250
		})
		$('#inputPhone').textbox({
			prompt : '联系方式',
			height : 30,
			width  : 250
		})
		$('#inputStartTime').textbox({
			height : 30,
			width  : 250
		})
	});
</script>
<body>
	<table id="repairMessages">
	</table>
	<div id="insertWindow" class="easyui-window" title=""
		style="width: 300px; padding: 10px;left:20%;top:20%">
		<div style="position: absolute;left:60px;text-align: center;">
			<h2 id="inputKind"></h2>
			<form id="inputForm" method="post">
				<table style="margin-bottom: 10px">
					<tr>
						<td><input id='no' name="no" type="hidden"></input></td>
					</tr>
					<tr>
						<td>姓名:</td>
						<td><input id='inputName' name="name" class="f1 easyui-textbox"></input></td>
					</tr>
					<tr>
						<td>身份证号:</td>
						<td><input id='inputId' name="id" class="f1 easyui-textbox"></input></td>
					</tr>
					<tr>
						<td>联系方式:</td>
						<td><input id='inputPhone' name="phone" class="f1 easyui-textbox"></input></td>
					</tr>
					<tr>
						<td>入职时间:</td>
						<td><input id='inputStartTime' name="startTime" class="easyui-datebox" required="required"></input></td>
					</tr>
				</table>
				<a id="submitButton" href="#" style="margin-right:10px">提交</a>
				<a id="noSubmitButton"> 取消</a>
			</form>
		</div>
	</div>
</body>
</html>