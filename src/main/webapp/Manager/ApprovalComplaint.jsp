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
<title>ApprovalComplaint</title>
<script>
$(function(){
	
	//-------------------------------------按条件查询
	$('#owner').textbox({    
	       
	    iconCls:'icon-man', 
	    iconAlign:'right'       
	});
	$('#cstarttime').textbox({    
		 
	    iconAlign:'right'  ,     
	});
	$('#cendtime').textbox({    
		 
	    iconAlign:'right'  ,     
	});
	//配置按钮
	$("#submit").linkbutton({
		iconCls: 'icon-search',
	    onClick:function(){
	    	$('#form').form('submit');
	    	//$("#dg").datagrid("reload");
	    }
	});
	
	//配置form表单
	$("#form").form({
		url:'SubmitManagerComplaint',     
	    success:function(data){
	    	var data = eval('(' + data + ')');
	    	if(data.success==true){
// 	    		$.messager.alert('消息提示',data.msg,'info',function(){
// 		    	});
						//配置datagrid
						$('#dg').datagrid({    
						    url:'SelectManagerComplaint', 
						    rownumbers:true,
						    fitColumns:true,
						    pageSize:10,
						    pageList:[10,20,30,40,50,60,70,80,90,100],
						    pagination:true,
						    columns:[[
								{field:'ck',title:'复选',checkbox:true,width:200},
								{field:'cid',title:'投诉编号',hidden:true,width:100},
								{field:'oid',title:'住户编号',hidden:false,width:100},
						        {field:'mno',title:'经理编号',width:100},    
						        {field:'cremark',title:'备注',width:100},    
						        {field:'cstarttime',title:'投诉时间',width:100},
						        {field:'cendtime',title:'截止时间',width:100},
						        {field:'cresult',title:'投诉结果',width:100,align:'right'}, 
						    ]] ,
						    toolbar: [{
								iconCls: 'icon-add',
								text:'受理投诉',
								handler: function(){
									$('#win1').window('open');
								}
							},'-',{
								iconCls: 'icon-edit',
								text:'反馈投诉',
								handler: function(){
									var rows  = $('#dg').datagrid('getSelections');
									if(rows.length==1){
										var row  = $('#dg').datagrid('getSelected');
										if(row!=null){
											$('#win2').window('open');
											$('#form2').form('load',{
												cid2:row.cid,
												cremark2:row.cremark,
												cresult2:row.cresult,
											});
					
					
										}else{
											$.messager.alert('消息提示','请选择反馈行！','info',function(){
							    				//$('#form1').form('clear');
							    			});
										}
									}else{
										if(rows.length==0){
											$.messager.alert('消息提示','请选择反馈行！','info',function(){
							    				//$('#form1').form('clear');
							    			});
										}else{
											$.messager.alert('消息提示','请选择单行反馈！','info',function(){
							    				//$('#form1').form('clear');
							    			});
										}
									}
								}
							},'-',{
			    				iconCls: 'icon-tip',
			    				text:'查看结果明细',
			    				handler: function(){
			    					var rows  = $('#dg').datagrid('getSelections');
			    					if(rows.length==1){
			    						var row  = $('#dg').datagrid('getSelected');
				    					//console.log(row)
				    						if(row!=null){
				    							$('#win4').window('open');
				    							$('#form4').form('load',{
				    								cid4:row.cid,
				    								cresult4:row.cresult,
				    							});
				    						}else{
				    							$.messager.alert('消息提示','请选择查看行！','info',function(){
				    			    				//$('#form1').form('clear');
				    			    			});
				    						}
			    					}else{
			    						if(rows.length==0){
			    							$.messager.alert('消息提示','请选择查看行！','info',function(){
			    			    				//$('#form1').form('clear');
			    			    			});
			    						}else{
			    							$.messager.alert('消息提示','请选择单行查看！','info',function(){
			    			    				//$('#form1').form('clear');
			    			    			});
			    						}
			    					}
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
	    	$("#dg").datagrid("reload");
	    }    
	});
	//-----------------------------------受理投诉
	//配置登录窗口
	$('#win1').window({    
	    width:210,    
	    height:120,    
	    title:'受理投诉',
	    minimizable:false,
	    maximizable:false,
	    draggable:true,
	});
	$('#win1').window('close');
	//配置提交按钮
	$('#submit1').linkbutton({    
	    iconCls: 'icon-ok',
	    onClick:function(){
	    	//表单内容提交
	    	var rows  = $('#dg').datagrid('getSelections');
	    	//alert(rows.length);
	    	if(rows.length!=0){
	    		var cids = [];
				for (var i = 0; i < rows.length; i++) {
					cids.push(rows[i].cid);
				}
				$.ajax({
					url:'AcceptComplaints',
					type:'post',
					dataType:'text',
					data:{'cids':cids},
					success:function(data){
						var data = eval('(' + data + ')');
				    	if(data.success==true){
				    		$.messager.alert('消息提示',data.msg,'info',function(){
					    		$('#win1').window('close');
					    		$('#dg').datagrid('reload');
					    	});
				    	}else{
				    		$.messager.alert('消息提示',data.msg,'info',function(){
				    			$('#win1').window('close');
			    			});
				    	}
					}
				});
	    	}else{
	    		$.messager.alert('消息提示','请选择行受理！','info',function(){
		    		$('#dg').datagrid('reload');
		    	});
	    	}
	    	$('#win1').window('close');
	    	$("#dg").datagrid("reload");
	    }
	});
	$('#exit1').linkbutton({    
	    iconCls: 'icon-cancel',
	    onClick:function(){
	    	$('#win1').window('close');
	    }
	});
	//-------------------------------------------------反馈结果
	//配置登录窗口
	$('#win2').window({    
	    width:500,    
	    height:400,    
	    title:'结果反馈窗口',
	    minimizable:false,
	    maximizable:false,
	    draggable:true,
	});
	$('#win2').window('close');
	//配置登录文本框
	$('#cid2').textbox({    
	    iconAlign:'right',
	    width:'230',
	    height:'30',
	    editable:false,
	});
	$('#cremark2').textbox({    
	    //buttonText:'用户名',    
	    iconAlign:'right',
	    prompt:'投诉内容',
	    width:'230',
	    height:'110',
	    editable:false,
	});
	$('#cresult2').textbox({    
	    //buttonText:'用户名',    
	    iconAlign:'right',
	    prompt:'填写处理结果',
	    width:'230',
	    height:'110',
	});
	//配置提交按钮
	$('#submit2').linkbutton({    
	    iconCls: 'icon-ok',
	    onClick:function(){
	    	//表单内容提交
	    	$('#win2').window('open');
	    	$('#form2').form('submit'); 
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
	    url:'UpdateComplaintResult',    
	    onSubmit: function(){    
	    	 
	    },    
	    success:function(data){    
	    	var data = eval('(' + data + ')');
	    	if(data.success==true){
	    		$("#dg").datagrid("reload");	
	    		$.messager.alert('消息提示',data.msg,'info',function(){
    				
    			});
	    		$('#win2').window('close');
	    	}else{
	    		$.messager.alert('消息提示',data.msg,'info',function(){
    				
    			});
	    	}
	    }    
	});
	//-------------------------------------------结果明细
	//配置登录窗口
	$('#win4').window({    
	    width:500,    
	    height:350,    
	    title:'结果明细',
	    minimizable:false,
	    maximizable:false,
	    draggable:true,
	});
	$('#win4').window('close');
	//配置登录文本框
	$('#cid4').textbox({    
	    iconAlign:'right',
	    width:'300',
	    height:'30',
	    editable:false,
	});
	$('#cresult4').textbox({    
	    //buttonText:'用户名',    
	    iconAlign:'right',
	    prompt:'输入投诉内容',
	    width:'300',
	    height:'150',
	});
	
	$('#exit4').linkbutton({    
	    iconCls: 'icon-clear',
	    onClick:function(){
	    	$('#win4').window('close');
	    }
	});
	//配置表单，不是提交
	$('#form4').form({    
	    url:'',    
	}); 
});

</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',title:'查询条件',split:true" style="height:100px;">
		<div style="margin-left: 100px;margin-top: 16px">
			<form id="form" method="post">
				住户编号：<input id="owner" name="owner" type="text" style="width:160px">
				投诉时间：<input id="cstarttime" name="cstarttime" class="easyui-datebox"  type="text" style="width:160px">
				截止时间：<input id="cendtime" name="cendtime" class="easyui-datebox"  type="text" style="width:160px">
				<a id="submit" href="#">查询</a>
			</form>
		</div>
	</div>   
       
    <div data-options="region:'center',title:'处理结果'" style="padding:5px;background:#eee;">
    	<table id="dg"></table>
    </div>   
	 <!-- 投诉受理窗口 -->
    <div id="win1">
    	<div style="margin-left: 30px;margin-top: 10px;">    
			<p>确认受理选中行？</p>
			<a id="submit1" href="#">提交</a>&nbsp;&nbsp;<a id="exit1" href="#">取消</a>
    	</div> 
	</div> 
	 <div id="win2">
		<form id="form2" method="post">  
    		<div style="margin-left: 70px;margin-top: 16px;">  
    			投诉单号：<input id="cid2" name="cid2" type="text" data-options="required:true" style="width:250px">
				<br><br>  
				投诉内容：<input id="cremark2" name="cremark2" class="easyui-textbox" data-options="multiline:true" >
				<br><br>
				处理结果：<input id="cresult2" name="cresult2" class="easyui-textbox" data-options="multiline:true" >
				<br><br>
				<a id="submit2" href="#">提交</a>&nbsp;&nbsp;<a id="exit2" href="#">取消</a>
    		</div>
	    </form>  
	</div>
	 <div id="win4"> 
	 	<form id="form4" method="post">  
    		<div style="margin-left: 70px;margin-top: 16px;">  
    			投诉单号：<input id="cid4" name="cid4" type="text" data-options="required:true" style="width:250px">
				<br><br>
				投诉结果：<input id="cresult4" name="cresult4" class="easyui-textbox" data-options="multiline:true" >
				<br><br>
				<a id="exit4" href="#">关闭</a>
    		</div>
    	</form> 
	</div>
</body>
</html>