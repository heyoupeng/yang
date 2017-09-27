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
$(function(){
	$('#win').window({   
		closed:true,
	    width:600,    
	    height:400,    
	    modal:true,
	    title:"添加房产信息",
	    collapsible:false,
	    minimizable:false,
	    maximizable:false,
	    closable:false,
	    draggable:false,
	    resizable:false,
	});  
	$('#win1').window({  
		closed:true,
	    width:600,    
	    height:400,    
	    modal:true,
	    title:"修改房产信息",
	    collapsible:false,
	    minimizable:false,
	    maximizable:false,
	    closable:false,
	    draggable:false,
	    resizable:false,
	});  
	
	$('#sbuild').combobox({    
	    url:'showP_build',    
	    valueField:'id',    
	    textField:'text',
	    panelHeight:70,
		editable:false,
	});  
	$('#sunit').combobox({    
	    url:'showP_unit',    
	    valueField:'id',    
	    textField:'text',
	    panelHeight:70,
		editable:false,
	});  
	$('#sfloor').combobox({    
	    url:'showP_floor',    
	    valueField:'id',    
	    textField:'text',
	    panelHeight:70,
		editable:false,
	});  
	$('#sroom').combobox({    
	    url:'showP_room',    
	    valueField:'id',    
	    textField:'text',
	    panelHeight:70,
		editable:false,
	});  
	$('#btn1').linkbutton({ 
		iconCls: 'icon-ok',
		onClick:function(){
			var sbuild = $("#sbuild").combobox('getValue');
			var sunit = $("#sunit").combobox('getValue');
			var sfloor = $("#sfloor").combobox('getValue');
			var sroom = $("#sroom").combobox('getValue');
			$('#dg').datagrid('load',{
				build: sbuild,
				unit: sunit,
				floor:sfloor,
				room:sroom,
			});
		}
	});
});
</script>
<body class="easyui-layout">   
    <div data-options="region:'north',minHeight:120,title:'条件查询',collapsible:false,split:true" style="height:100px;">
    <div style="text-align: center;padding-top: 20px;" >
  楼栋号：<input id="sbuild"  name="sbuild"  >	
  单元号：<input id="sunit"  name="sunit"  >
  楼层号：<input id="sfloor"  name="sfloor"  >
  房间号：<input id="sroom"  name="sroom"  >
  <br><br>
  <a id="btn1" href="#">确认</a> 
  </div>
    </div>   
    <div data-options="region:'center',title:'信息显示'" style="padding:5px;background:#eee;">
    <table id="dg"></table> 
    
    
    
    <!-- 增加界面 --> 
<div id="win">
<form id="ff" method="post"> 
<div style="text-align: center;padding-top: 50px;">
楼栋号：<input id="build"  name="build" type="text" style="width:300px">
<br><br>
单元号：<input id="unit" name="unit" type="text" style="width:300px">
<br><br>
楼层号：<input id="floor" name="floor" type="text" style="width:300px">
<br><br>
房间号：<input id="room" name="room" type="text" style="width:300px">
<br><br>
<div style="position: relative;right:7px;">
对应业主：<input id="ownerId" name="ownerId"  style="width:300px">
</div>
<br>
<div style="position: relative;right:7px;">
建筑面积：<input id="area" name="area" type="text" style="width:300px">
</div>
<br>
<div style="position: relative;left:7px;">
备注：<input id="remark" name="remark" type="text" style="width:300px">
</div>
<br>
<a id="btn2" href="#">确认</a>    
<a id="btn3" href="#">取消</a>    
</div>  
</form>
</div>  


<!-- 修改界面 -->
<div id="win1">
<form id="ff1" method="post"> 
<div style="text-align: center;padding-top: 50px;">
楼栋号：<input id="build1"  name="build1" type="text" style="width:300px">
<br><br>
单元号：<input id="unit1" name="unit1" type="text" style="width:300px">
<br><br>
楼层号：<input id="floor1" name="floor1" type="text" style="width:300px">
<br><br>
房间号：<input id="room1" name="room1" type="text" style="width:300px">
<br><br>
<div style="position: relative;right:7px;">
对应业主：<input id="ownerId1" name="ownerId1"  style="width:300px">
</div>
<br>
<div style="position: relative;right:7px;">
建筑面积：<input id="area1" name="area1" type="text" style="width:300px">
</div>
<br>
<div style="position: relative;left:7px;">
备注：<input id="remark1" name="remark1" type="text" style="width:300px">
</div>
<br>
<a id="btn4" href="#">确认</a>    
<a id="btn5" href="#">取消</a>    
</div>  
</form>
</div>  
    </div>   
</body>  
<script type="text/javascript">
$(function(){
	//1，3新增按钮
	$('#btn2').linkbutton({ 
		iconCls: 'icon-ok',
		onClick:function(){
			$('#ff').form('submit');
		}
	});
	$('#btn3').linkbutton({   
		iconCls: 'icon-no',
		onClick:function(){
			 $('#build').textbox('clear');
		       $('#unit').textbox('clear');
		     $('#floor').textbox('clear');
		     $('#room').textbox('clear');
		     $('#ownerId').textbox('clear');
		     $('#remark').textbox('clear');
		     $('#area').textbox('clear');
			$('#win').window("close",true);
		}
	});  
	//4,5修改按钮
	$('#btn4').linkbutton({ 
		iconCls: 'icon-ok',
		onClick:function(){
			$('#ff1').form('submit');
		}
	});
	$('#btn5').linkbutton({   
		iconCls: 'icon-no',
		onClick:function(){
			$('#win1').window("close",true);
		}
	});  
	$('#build').textbox({    
		width:200,
		height:40,
		prompt:"请输入楼栋号",
		type:"text",
		iconCls:'icon-man', 
		iconAlign:'left'       
	})
	$('#unit').textbox({    
		width:200,
		height:40,
		prompt:"请输入单元号",
		type:"text",
		iconCls:'icon-man', 
		iconAlign:'left'       
	})
	$('#floor').textbox({    
		width:200,
		height:40,
		prompt:"请输入楼层号",
		type:"text",
		iconCls:'icon-man', 
		iconAlign:'left'       
	})
	$('#room').textbox({    
		width:200,
		height:40,
		prompt:"请输入房间号",
		type:"text",
		iconCls:'icon-man', 
		iconAlign:'left'       
	})
	$('#ownerId').combobox({
	    url:'showO_ownerId',
	    width:200,
		height:40,
	    valueField:'id',    
	    textField:'text',
	    panelHeight:70,
		editable:false,
	});  
	$('#area').textbox({    
		width:200,
		height:40,
		prompt:"请输入建筑面积",
		type:"text",
		iconCls:'icon-man', 
		iconAlign:'left'       
	})
		$('#remark').textbox({    
		width:200,
		height:40,
		prompt:"请输入备注",
		type:"text",
		iconCls:'icon-man', 
		iconAlign:'left'       
	})
	$('#build1').textbox({    
		width:200,
		height:40,
		type:"text",
		readonly:true,
		iconCls:'icon-man', 
		iconAlign:'left'       
	})
	$('#unit1').textbox({    
		width:200,
		height:40,
		type:"text",
		readonly:true,
		iconCls:'icon-man', 
		iconAlign:'left'       
	})
	$('#floor1').textbox({    
		width:200,
		height:40,
		type:"text",
		readonly:true,
		iconCls:'icon-man', 
		iconAlign:'left'       
	})
	$('#room1').textbox({    
		width:200,
		height:40,
		type:"text",
		readonly:true,
		iconCls:'icon-man', 
		iconAlign:'left'       
	})
	$('#ownerId1').combobox({
	    url:'showO_ownerId',
	    width:200,
		height:40,
	    valueField:'id',    
	    textField:'text',
	    panelHeight:70,
		editable:false,
	});  
	$('#area1').textbox({    
		width:200,
		height:40,
		type:"text",
		iconCls:'icon-man', 
		iconAlign:'left'       
	})
		$('#remark1').textbox({    
		width:200,
		height:40,
		prompt:"请输入备注",
		type:"text",
		iconCls:'icon-man', 
		iconAlign:'left'       
	})
	//添加表单
	$('#ff').form({    
	    url:'insertPropertyinfo',    
	    onSubmit: function(){    
	        var build = $('#build').val();
	        var unit = $('#unit').val();
	        var floor = $('#floor').val();
	        var room = $('#room').val();
	        var area = $('#area').val();
	        if(build.length==0||unit.length==0||area.length==0||floor.length==0||room.length==0)
	        	{
	        	$.messager.alert('错误', '楼栋号/单元号/楼层号/房间号/面积有一项为空', 'error');
	        	return false;
	        	}
	    },    
	    success:function(data){    
	        var s = eval("("+data+")");
	        if(s.state=='1')
	        	{
	        	$.messager.alert('成功', '插入房产信息成功', 'info');
	        	$('#win').window("close",true);
	        	 $('#build').textbox('clear');
			       $('#unit').textbox('clear');
			     $('#floor').textbox('clear');
			     $('#room').textbox('clear');
			     $('#ownerId').textbox('clear');
			     $('#remark').textbox('clear');
			     $('#area').textbox('clear');
	        	$('#dg').datagrid('load');
	        	}
	        else if(s.state=='2')
	        {
	        	$.messager.alert('错误', '楼栋号/单元号/楼层号/房间号/面积有一项不为数字', 'error');
	        }
	        else
	        	{
	        	$.messager.alert('错误', '插入房产信息失败', 'error');
	        	}
	    }    
	});
	
	//修改表单
	$('#ff1').form({    
	    url:'updatePropertyinfo',
	    onSubmit:function(){
	    	var area = $('#area1').val();
	    	if(area.length==0)
	    		{
	    		$.messager.alert('错误', '面积为空', 'error');
	    		return false;
	    		}
	    },
	    success:function(data){    
	        var s = eval("("+data+")");
	        if(s.state=='1')
	        	{
	        	$.messager.alert('成功', '修改房产信息成功', 'info');
	        	$('#win1').window("close",true);
	        	$('#dg').datagrid('load');
	        	}
	        else if(s.state=='2')
	        {
	        	$.messager.alert('错误', '面积不为数字', 'error');
	        }
	        else
	        	{
	        	$.messager.alert('错误', '修改房产信息失败', 'error');
	        	}
	    }    
	});    
	
	$('#dg').datagrid({    
	    url:'showPropertyinfo',    
	    striped:true,
	    rownumbers:true,
	    fitColumns:true,
	    nowrap:false,
	    pagination : true,//分页
		pageSize : 3,//初始化数据行数
		pageList : [ 3, 6, 9 ],//每页数据行数,
		checkOnSelect:true,
	    columns:[[ 
	               {field : 'ck',checkbox : true},    
	        {field:'p_building',title:'楼栋号',width:100},    
	        {field:'p_unit',title:'单元号',width:100},    
	        {field:'p_floor',title:'楼层号',width:100},
	        {field:'p_room',title:'房间号',width:100}, 
	        {field:'p_ownerId',title:'对应业主编号',width:100}, 
	        {field:'p_ownerName',title:'对应业主姓名',width:100}, 
	        {field:'p_area',title:'建筑面积',width:100}, 
	        {field:'p_remark',title:'备注',width:100}, 
	    ]],
	    toolbar:[{
            text:'添加房产信息',
            iconCls:'icon-add',
            handler:function(){
            	$('#win').window("open",true);
            }
        },'-',
        {
            text:'修改房产信息',
            iconCls:'icon-cut',
            handler:function(){
            	var checks =  $('#dg').datagrid('getSelections');
            	if(checks.length==1)
            		{
            		$('#build1').textbox('setValue',checks[0].p_building);
            		$('#unit1').textbox('setValue',checks[0].p_unit);
            		$('#floor1').textbox('setValue',checks[0].p_floor);
            		$('#room1').textbox('setValue',checks[0].p_room);
            		$('#area1').textbox('setValue',checks[0].p_area);
            		$('#remark1').textbox('setValue',checks[0].p_remark);
            		$('#ownerId1').combobox('select',checks[0].p_ownerId);
            		$('#win1').window("open",true);
            		}
            }
        },'-',{
            text:'删除房产信息',
            iconCls:'icon-save',
            handler:function(){
            	var checks =  $('#dg').datagrid('getSelections');
            	var msg = new Array();
            	for (var i = 0; i < checks.length; i++) {
            		var str ;
            		str = checks[i].p_building+','+checks[i].p_unit
            		      +','+checks[i].p_floor+','+checks[i].p_room;
            		console.log(str);
            		msg.push(str);
				}
            	if(checks.length!='0')
        		{
        		 $.ajax({
                      url:'deletePropertyinfo',
                      type:'post',
                      data:{'ids':msg},
                      success:function(data){	
                    	  var s = eval("("+data+")");
              	        if(s.state=='1')
              	        	{
              	        	$.messager.alert('成功', '删除房产信息成功', 'info');
              	        	$('#dg').datagrid('load');
              	        	}
              	        else
              	        	{
              	        	$.messager.alert('错误', '删除房产信息失败', 'error');
              	        	}
                      }
        		 })
        		}
            }
        }],
	});  
});
</script>
</html>