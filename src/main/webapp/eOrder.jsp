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
	src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>
</head>
<script type="text/javascript">
$(function(){
	$('#rep').combobox({  
		height:35,
		width:200,
	    url:'getRep',    
	    valueField:'id',    
	    textField:'text'  ,
	    panelMaxHeight:60,
	    editable:false
	});
	function tip(tip){
		$.messager.show({
			title:'提示',
			msg:tip,
			timeout:1000,
			showType:'slide',
			style:{
				right:'',
				top:document.body.scrollTop+document.documentElement.scrollTop,
				bottom:''
			}
		});
	};  
	$('#updateWin').window({    
	    width:500,    
	    height:400,    
	    modal:true ,
	    title:"任务分配",
	    closed:true
	});
	
	$("#state").combobox({
		valueField: 'id',
		textField: 'text',
		panelMaxHeight:70,
	    editable:false,
		data: [{
			text: '全部',
			id: 'all',
			selected:true
		},{
			text: '已分配任务',
			id: 'ok'
		},{
			text: '未分配任务',
			id: 'no'
		}],
		onSelect:function(n){
			console.log(n);
			$('#repair').datagrid('load',{
				state: n.id
			});
		}
	});
/* 	$("input").textbox({
		height:35,
		width:200,
		hidden:true,
	}); */
	$(".myinput").textbox({
		height:35,
		width:200,
		hidden:true,
	});
	$("#updateForm").form({
		url : "assignment",
		onSubmit : function(param) {
		//	param.vNo = getTreeSelected($("#vNo"));
		},
		success : function(data) {
			var data = eval('(' + data + ')');
			console.log(data.bool);
			if (data.bool) {
				tip("任务分配成功");
				$('#updateForm').form('clear');
				$('#repair').datagrid('reload');
				$('#rep').combobox('reload');
			}
		}
	});
	$("#uok").linkbutton({
		onClick:function(){
			if($("#rep").combobox("getValue") != ""){
				$('#updateForm').form('submit'); 
				$('#updateWin').window('close');
			}
			else{
				tip("请选择维修人员")
			}
			
	    	
	    }
	});
	$("#uno").linkbutton({
		onClick : function() {
			$('#updateForm').form('clear');
			$('#updateWin').window('close');
		}
	});
	$("#repair").datagrid({
		url : "eOrder",
		queryParams: {
			state: 'all',
		},
		pagination : true,
		pageSize : 6,
		pageList : [ 3, 6, 9 ],
		rownumbers : true,
		columns : [ [ {
			field : 'num',
			title : 'ck',
			checkbox : true
		}, {
			field : 'OW_id',
			hidden : true,
			title : '报修id',
			width : '20%'
		}, {
			field : 'OW_remark',
			title : '报修原因',
			width : '15%'
		}, {
			field : 'o_name',
			title : '业主姓名',
			width : '10%'
		}, {
			field : 'r_name',
			title : '维修人员姓名',
			width : '15%'
		}, {
			field : 'OW_starttime',
			title : '报修时间',
			width : '15%'
		},{
			field : 'state',
			title : '状态',
			width : '15%'
		}] ] ,
		toolbar : [{
				text:'任务分配',
				iconCls:'icon-edit',
				handler:function(){
					var tr = $('#repair').datagrid('getSelected');
					if(tr != null){
						if(tr.r_name != ""){
							tip("该任务已分配,请勿重复分配");
						}
						else{
							$("#updateForm").form('load', tr);
							$('#updateWin').window('open');
						}
						
					}
				}
			}]
		
	});
});

</script>
<body>
	<label>任务状态：</label>
	<input id = "state" name = "rstate">
	<table id="repair"></table>
	<div id="updateWin">
		<div style="margin: 80px;">
			<form id = "updateForm" method="post">
			<p>系统默认单条数据分配任务，如果您选了多个，默认为选中的第一个</p>
			<p>已接任务大于10条的维修人员将不会出现在维修人员中</p>
			<table>
				<tr>
					<td><label>业主：</label></td>
					<td><input class="myinput" name="o_name" readonly="readonly"></td>
				</tr>
				<tr>
					<td><label>报修原因：</label></td>
					<td><input class="myinput" name="OW_remark" readonly="readonly"></td>
				</tr>
				<tr>
					<td><label>报修时间：</label></td>
					<td><input class="myinput" name="OW_starttime" readonly="readonly"></td>
				</tr>
				<tr>
					<td hidden="hidden"><input id="OW_id" name="OW_id" type = "text"></td>
					<td><label>维修人员：</label></td>
					<td><input id="rep" name="pep"></td>
				</tr>
				<tr>
					<td><a id="uno">取消</a></td>
					<td><a id="uok">确定</a></td>
				</tr>
			</table>
		</form> 
	</div>
		<!-- <form id = "updateForm" method="post">
			<input name="o_name" readonly="readonly">
			<input name="OW_remark" readonly="readonly">
			<input name="OW_starttime" readonly="readonly">
			<input id="OW_id" name="OW_id" type = "text">
			<input id="rep" name="pep" value="c">
		</form> -->
	</div>
</body>
</html>