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
	    height:500,    
	    modal:true,
	    title:"审批辞职申请",
	    collapsible:false,
	    minimizable:false,
	    maximizable:false,
	    closable:false,
	    draggable:false,
	    resizable:false,
	});  
});
</script>
<body class="easyui-layout">   
    <div data-options="region:'center',title:'辞职申请审批'" style="padding:5px;background:#eee;">
    <table id="dg"></table>  
<div id="win">
<form id="ff" method="post"> 
<div style="text-align: center;padding-top: 50px;">
申请人编号：<input id="id"  name="id" type="text" style="width:300px">
<br><br>
申请人姓名：<input id="name" name="name" type="text" style="width:300px">
<br><br>
<div style="position: relative;top:7px;left:5px;">
申请内容：<input id="content" name="content" type="text" style="width:300px">
</div>
<br><br>
<div style="position: relative;top:7px;left:5px;">
发起时间：<input id="starttime" name="starttime" type="text" style="width:300px">
</div>
<br><br>
<div style="position: relative;top:7px;left:16px;">
状态：<input id="state" name="state" type="text" style="width:300px">
</div>
<br><br>
<div style="position: relative;top:7px;left:16px;">
结果：<input id="cc" name="dept" >  
</div>
<br><br>
<div style="position: relative;top:7px;left:16px;">
备注：<input id="result" name="result" type="text" style="width:300px">
</div>
<br><br>
<a id="btn1" href="#">确认</a>    
<a id="btn2" href="#">取消</a>    
</div>  
</form>
</div>  
    </div>   
</body>  
<script type="text/javascript">
$('#btn1').linkbutton({ 
	iconCls: 'icon-ok',
	onClick:function(){
		$('#ff').form('submit');
	}
});
$('#btn2').linkbutton({   
	iconCls: 'icon-no',
	onClick:function(){
		$('#win').window("close",true);
	}
});  


$('#cc').combobox({    
	width:200,
	height:40,
	panelHeight:60,
	editable:false,
    valueField:'id',    
    textField:'text',
    data: [{
		id: '通过',
		text: '通过'
	},{
		id: '否决',
		text: '否决'
	}],
});  

$('#id').textbox({    
	width:200,
	height:40,
	editable:false,
	type:"text",
	iconCls:'icon-man', 
	iconAlign:'left'       
})
$('#name').textbox({    
	width:200,
	height:40,
	editable:false,
	type:"text",
	iconCls:'icon-man', 
	iconAlign:'left'       
})
$('#content').textbox({    
	width:200,
	height:90,
	editable:false,
	multiline:true,
	type:"text",
	iconAlign:'left'        
})
$('#starttime').textbox({    
	width:200,
	height:40,
	editable:false,
	type:"text",
	iconAlign:'left'       
})
$('#state').textbox({    
	width:200,
	height:40,
	editable:false,
	type:"text",
	iconAlign:'left'       
})
$('#result').textbox({    
	width:200,
	height:90,
	multiline:true,
	type:"text",
	iconAlign:'left'     
})
$('#ff').form({    
	    url:'managerApproval',    
	    onSubmit: function(){    
	        var state = $('#cc').combobox('getValue');
	        if(state.length==0)
	        	{
	        	$.messager.alert('错误', '结果为空，不允许提交', 'error');
	        	return false;
	        	}
	    },    
	    success:function(data){    
	        var s = eval("("+data+")");
	        if(s.state=='1')
	        	{
	        	$.messager.alert('成功', '审批成功', 'info');
	        	$('#win').window("close",true);
	        	 $('#name').textbox('clear');
			       $('#id').textbox('clear');
			     $('#phone').textbox('clear');
			     $('#remark').textbox('clear');
	        	$('#dg').datagrid('load');
	        	}
	        else
	        	{
	        	$.messager.alert('错误', '审批失败', 'error');
	        	}
	    }    
	});

$(function(){
	$('#dg').datagrid({    
	    url:'showQuitWork',    
	    striped:true,
	    rownumbers:true,
	    fitColumns:true,
	    pagination : true,//分页
		pageSize : 3,//初始化数据行数
		pageList : [ 3, 6, 9 ],//每页数据行数,
		checkOnSelect:true,
	    columns:[[ 
	               {field : 'ck',checkbox : true},
	        {field:'e_no',title:'申请人编号',width:100},   
	        {field:'q_name',title:'申请人姓名',width:100},
	        {field:'q_content',title:'申请内容',width:100},    
	        {field:'q_starttime',title:'发起时间',width:100}, 
	        {field:'q_endtime',title:'审批时间',width:100}, 
	        {field:'q_result',title:'状态',width:100},
	    ]],
	    toolbar:[{
            text:'审批辞职申请',
            iconCls:'icon-add',
            handler:function(){
            	var checks =  $('#dg').datagrid('getSelections');
            	if(checks.length==1&&checks[0].q_result=='未审批')
            		{
            		$('#id').textbox('setValue',checks[0].e_no);
            		$('#name').textbox('setValue',checks[0].q_name);
            		$('#content').textbox('setValue',checks[0].q_content);
            		$('#starttime').textbox('setValue',checks[0].q_starttime);
            		$('#state').textbox('setValue',checks[0].q_result);
            		$('#win').window("open",true);
            		}
            }
        }],
	})
});
</script>
</html>