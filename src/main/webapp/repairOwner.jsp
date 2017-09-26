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
	    modal:true,
	    closed:true
	});
	$("input").textbox({
		height:35,
		width:200,
		hidden:true,
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
			text: '已完成任务',
			id: 'ok'
		},{
			text: '未完成任务',
			id: 'no'
		}],
		onSelect:function(n){
			console.log(n);
			$('#repair').datagrid('load',{
				state: n.id
			});
		}
	});
	
	$("#updateForm").form({
		url : "okOwnerOrder",
		onSubmit : function(param) {
			return testResult();
		},
		success : function(data) {
			var data = eval('(' + data + ')');
			console.log(data.bool);
			if (data.bool) {
				tip("提交维护状态成功");
				$('#updateForm').form('clear');
				$('#repair').datagrid('reload');
			}
		}
	});
	function testResult(){
		var remark = $("#result").val();
		if(remark == ""){
			return false;
		}
		else{
			$('#updateWin').window('close');
			return true;
		}
	}
	$("#uok").linkbutton({
		onClick:function(){
	    	$('#updateForm').form('submit'); 
	    }
	});
	$("#uno").linkbutton({
		onClick : function() {
			$('#updateForm').form('clear');
			$('#updateWin').window('close');
		}
	});
	$("#repair").datagrid({
		url : "rownerOrder",
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
			field : 'o_phone',
			title : '业主电话',
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
			text:'完成任务',
			iconCls:'icon-edit',
			handler:function(){
				var tr = $('#repair').datagrid('getSelected');
				if(tr != null){
					if(tr.state == "已完成任务"){
						tip("该任务已完成，不需要重复提交");
					}
					else{
						$("#updateForm").form('load', tr);
						$('#updateWin').window('open');
					}
					
				}
				/* $.messager.confirm('确认','您确认完成这些任务吗吗？',function(r){    
				    if (r){   
				    	var repair = $('#repair').datagrid('getSelections');
						var owid = new Array();
						for (var i = 0; i < repair.length; i++) {
							owid[i] = repair[i].OW_id;
						}
						console.log(owid);
						$.ajax({
							url:"okOwnerOrder",
							type:'post',
							dataType:'json',
							data:{
								"owid":owid
							},
							success:function(data){
								console.log(data);
								tip("您已完成" + data.len +"条任务");
								$("#repair").datagrid('reload');
							}
						});
				    }    
				}); */
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
			<p>系统默认提交单条任务，如果您选了多个，默认为选中的第一个</p>
			<table>
				<tr>
					<td hidden="hidden"><input id="OW_id" name="OW_id" type = "text"></td>
					<td><label>业主姓名：</label></td>
					<td><input class="myinput" name="o_name" readonly="readonly"></td>
				</tr>
				<tr>
					<td><label>报修原因：</label></td>
					<td><input class="myinput" name="OW_remark" readonly="readonly"></td>
				</tr>
				<tr>
					<td><label>维修结果：</label></td>
					<td><textarea id="result" name="ow_result"></textarea></td>
				</tr>
				<tr>
					<td><a id="uno">取消</a></td>
					<td><a id="uok">确定</a></td>
				</tr>
			</table>
		</form> 
	</div>
	</div>
</body>
</html>