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
	$(function() {
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
		$(".add").textbox({
			height:35,
			width:200,
			hidden:true,
		});
		$(".update").textbox({
			height:35,
			width:200,
			hidden:true,
		});
	    $('#an').numberbox({
	    	height:35,
			width:200,
	        min:0,
	        precision:0
	    });
	    $('#un').numberbox({
	    	height:35,
			width:200,
	        min:0,
	        precision:0
	    });
		$("#type").combo({
			panelHeight:70
		});
		$("#type1").combo({
			panelHeight:70
		});
		$("#type").textbox({
			height:35,
			width:200,
			hidden:true,
		});
		$("#type1").textbox({
			height:35,
			width:200,
			hidden:true,
		});
		$("#addForm").form({
			url:"addVillage",
			onSubmit: function(){  
				return testAdd();
		    },   
			success:function(data){
				var data = eval('(' + data + ')');
				console.log(data.bool);
				if(data.bool){
					tip("添加物品信息成功");
		    		$('#addForm').form('clear');
		    		$('#village').datagrid('reload');
				}
			}
		});
		
		$("#updateForm").form({
			url : "updateVillage",
			onSubmit : function(param) {
				return testUpdate();
			},
			success : function(data) {
				var data = eval('(' + data + ')');
				console.log(data.bool);
				if (data.bool) {
					tip("修改物品信息成功");
					$('#updateForm').form('clear');
					$('#village').datagrid('reload');
				}
			}
		});
		$("#aok").linkbutton({
			onClick:function(){
		    	$('#addForm').form('submit'); 
		    }
		});
		
		function testAdd(){
			var adds = $(".add");
			for(var i = 0 ; i < adds.length; i ++){
				if($(adds[i]).textbox("getValue") == ''){
					return false;
				} 
			}
			var an = $("#an");
			if(an.numberbox("getValue") == ""){
				return false;
			}
    		$('#addWin').window('close');
			return true;
		};
		function testUpdate(){
			var updates = $(".update");
			for(var i = 0 ; i < updates.length; i ++){
				if($(updates[i]).textbox("getValue") == ''){
					return false;
				} 
			}
			var un = $("#un");
			if(un.numberbox("getValue") == ""){
				return false;
			}
			$('#updateWin').window('close');
			return true;
		};
		$("#test").linkbutton({
			onClick:function(){
				$('#village').datagrid('load',{
					code: '01',
					name: 'name01'
				});
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
		$("#village").datagrid({
			url : "village",
			pagination : true,
			pageSize : 6,
			pageList : [ 3, 6, 9 ],
			rownumbers : true,
			columns : [ [ {
				field : 'num',
				title : 'ck',
				checkbox : true
			}, {
				field : 'vNo',
				hidden : true,
				title : '物品id',
				width : '20%'
			}, {
				field : 'vName',
				title : '物品名',
				width : '20%'
			}, {
				field : 'vNumber',
				title : '物品数量',
				width : '20%'
			}, {
				field : 'vRemark',
				title : '物品备注',
				width : '20%'
			}, {
				field : 'typeName',
				title : '物品类型',
				width : '20%'
			} ] ],
			 toolbar : [{
					text:'添加物品',
					iconCls:'icon-add',
					handler:function(){
						$('#addWin').window('open');
					}
				},{
					text:'删除物品',
					iconCls:'icon-cut',
					handler:function(){
						$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
						    if (r){   
						    	var vill = $('#village').datagrid('getSelections');
								var vno = new Array();
								for (var i = 0; i < vill.length; i++) {
									vno[i] = vill[i].vNo;
								}
								console.log(vno);
								$.ajax({
									url:"deleteVillage",
									type:'post',
									dataType:'json',
									data:{
										"vno":vno
									},
									success:function(data){
										console.log(data);
										tip("删除成功" + data.len +"条数据");
										$("#village").datagrid('reload');
									}
									
								});
						    }    
						});
						
					}
				},'-',{
					text:'修改物品信息',
					iconCls:'icon-edit',
					handler:function(){
						var tr = $('#village').datagrid('getSelected');
						console.log(tr.typeName);
						$("#updateForm").form('load', tr);
						$('#type1').combobox('select', tr.typeName);
						$('#updateWin').window('open');
					}
				}]
		});
	});
</script>
<body>
	<table id="village"></table>
	<div id="addWin" class="easyui-window" title="增加物品信息" data-options="modal:true,closed:true,iconCls:'icon-save'">
			<div style="margin: 80px;">
				<form id = "addForm" method="post">
				<table>
					<tr>
						<td><label>物品名称：</label></td>
						<td><input name="vname" class="add" type = "text"></td>
					</tr>
					<tr>
						<td><label>物品数量：</label></td>
						<td><input name="vnumber" id="an" type="text"></td>
					</tr>
					<tr>
						<td><label>物品状态：</label></td>
						<td><input name="vremark" class="add" type="text"></td>
					</tr>
					<tr>
						<td><label>物品类型：</label></td>
						<td>
							 <select id="type" class="easyui-combobox" name="typename" style="width:200px;">   
							    <option selected="selected">小区物品</option>   
							    <option>物业物品</option>   
							</select>
						</td>
					</tr>
					<tr>
						<td><a id="ano">取消</a></td>
						<td><a id="aok">确定</a></td>
					</tr>
				</table>
				
			</form>
		</div>
	</div>
		<div id="updateWin" class="easyui-window" title="修改物品信息" data-options="modal:true,closed:true,iconCls:'icon-save'">
			<div style="margin: 80px;">
				<form id = "updateForm" method="post">
				<table>
					<tr>
						<td hidden="hidden"><input id="vNo" name="vNo" type = "text"></td>
						<td><label>物品名称：</label></td>
						<td><input name="vName" class="update" type = "text"></td>
					</tr>
					<tr>
						<td><label>物品数量：</label></td>
						<td><input name="vNumber" id="un" type="text"></td>
					</tr>
					<tr>
						<td><label>物品状态：</label></td>
						<td><input name="vRemark" class="update" type="text"></td>
					</tr>
					<tr>
						<td><label>物品类型：</label></td>
						<td>
							<select id="type1" class="easyui-combobox" id="typeName" name="typename" style="width:200px;">   
							    <option value="小区物品">小区物品</option>   
							    <option value="物业物品">物业物品</option>   
							</select>
						</td>
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