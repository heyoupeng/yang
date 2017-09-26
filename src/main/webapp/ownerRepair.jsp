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
	$('#addWin').window({    
	    width:500,    
	    height:400,    
	    modal:true   
	});
	$('#updateWin').window({    
	    width:500,    
	    height:400,    
	    modal:true   
	});
	$("input").textbox({
		height:35,
		width:200,
		hidden:true,
	});
	$("#addForm").form({
		url:"addOwnerOrder",
		success:function(data){
			var data = eval('(' + data + ')');
			console.log(data.bool);
			if(data.bool){
				tip("报修成功");
	    		$('#addForm').form('clear');
	    		$('#addWin').window('close');
	    		$('#repair').datagrid('reload');
			}
		}
	});
	
	$("#updateForm").form({
		url : "updateVillage",
		onSubmit : function(param) {
		//	param.vNo = getTreeSelected($("#vNo"));
		},
		success : function(data) {
			var data = eval('(' + data + ')');
			console.log(data.bool);
			if (data.bool) {
				tip("修改物品信息成功");
				$('#updateForm').form('clear');
				$('#updateWin').window('close');
				$('#repair').datagrid('reload');
			}
		}
	});
	$("#aok").linkbutton({
		onClick:function(){
	    	$('#addForm').form('submit'); 
	    }
	});
	$("#ano").linkbutton({
		onClick : function() {
			$('#addForm').form('clear');
			$('#addWin').window('close');
		}
	});
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
		url : "ownerOrder",
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
			field : 'r_name',
			title : '维修人员',
			width : '10%'
		}, {
			field : 'r_phone',
			title : '维修人员电话',
			width : '15%'
		}, {
			field : 'OW_starttime',
			title : '报修时间',
			width : '15%'
		}, {
			field : 'OW_endtime',
			title : '结束时间',
			width : '15%'
		} ,{
			field : 'state',
			title : '状态',
			width : '15%'
		} 
		] ],
		 toolbar : [{
				text:'报修',
				iconCls:'icon-add',
				handler:function(){
					$('#addWin').window('open');
				}
			},{
				text:'撤销报修',
				iconCls:'icon-cut',
				handler:function(){
					$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
					    if (r){   
					    	var repair = $('#repair').datagrid('getSelections');
							var owids = new Array();
							for (var i = 0; i < repair.length; i++) {
								owids[i] = repair[i].OW_id;
							}
							$.ajax({
								url:"deleteOrder",
								type:'post',
								dataType:'json',
								data:{
									"owids":owids
								},
								success:function(data){
									console.log(data);
									tip("删除成功" + data.sum +"条数据"+",其中已完成任务默认为不可删除");
									$("#repair").datagrid('reload');
								}
								
							});
					    }    
					});
					
				}
			}]
	});
});

</script>
<body>
	<table id="repair"></table>
	<div id="addWin" class="easyui-window" title="业主报修" data-options="modal:true,closed:true,iconCls:'icon-save'">
			<div style="margin: 80px;">
				<form id = "addForm" method="post">
				<table>
					<tr>
						<td><label>报修内容：</label></td>
						<td><textarea name="remark" rows="3" cols="25"></textarea></td>
					</tr>
					
					<tr>
						<td><a id="ano">取消</a></td>
						<td><a id="aok">确定</a></td>
					</tr>
				</table>
				
			</form>
		</div>
	</div>
</body>
</html>