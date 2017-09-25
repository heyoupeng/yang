<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
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
	    title:"添加业主信息",
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
	    title:"修改业主信息",
	    collapsible:false,
	    minimizable:false,
	    maximizable:false,
	    closable:false,
	    draggable:false,
	    resizable:false,
	});  
	
	$('#win2').window({  
		closed:true,
	    width:600,    
	    height:400,    
	    modal:true,
	    title:"导入Excel",
	    collapsible:false,
	    minimizable:false,
	    maximizable:false,
	    closable:false,
	    draggable:false,
	    resizable:false,
	});  
	
	$('#sstate').textbox({    
		width:150,
		height:30,
		prompt:"请输入业主居住状态",
		type:"text",
		iconCls:'icon-man', 
		iconAlign:'left'       
	})
	$('#sname').textbox({    
		width:150,
		height:30,
		prompt:"请输入业主姓名",
		type:"text",
		iconCls:'icon-man', 
		iconAlign:'left'       
	})
	$('#sid').textbox({    
		width:150,
		height:30,
		prompt:"请输入业主身份证号",
		type:"text",
		iconCls:'icon-man', 
		iconAlign:'left'       
	})
	$('#sphone').textbox({    
		width:150,
		height:30,
		prompt:"请输入业主电话",
		type:"text",
		iconCls:'icon-man', 
		iconAlign:'left'       
	})
	
	$('#btn8').linkbutton({ 
		iconCls: 'icon-ok',
		onClick:function(){
			var sname = $("#sname").val();
			var sid = $("#sid").val();
			var sphone = $("#sphone").val();
			var sstate = $("#sstate").val();
			$('#dg').datagrid('load',{
				name: sname,
				id: sid,
				phone:sphone,
				state:sstate
			});
		}
	});
});
</script>
<body  class="easyui-layout">
  <div data-options="region:'north',title:'条件输入',collapsible:false,split:true" style="height:100px;">
  <div style="text-align: center;padding-top: 20px;" >
  姓名：<input id="sname"  name="sname" type="text" >
  身份证号：<input id="sid"  name="sid" type="text" >
  电话：<input id="sphone"  name="sphone" type="text" >
  居住状态：<input id="sstate"  name="sstate" type="text" >
  <a id="btn8" href="#">确认</a> 
  </div>
  </div>     
 <div data-options="region:'center',title:'显示信息'" style="padding:5px;background:#eee;">
 <table id="dg"></table> 
 
 <!-- 增加界面 --> 
<div id="win">
<form id="ff" method="post"> 
<div style="text-align: center;padding-top: 50px;">
姓名：<input id="name"  name="name" type="text" style="width:300px">
<br><br>
<div style="position: relative;top:7px;right:14px;">
身份证号：<input id="id" name="id" type="text" style="width:300px">
</div>
<br><br>
电话：<input id="phone" name="phone" type="text" style="width:300px">
<br><br>
备注：<input id="remark" name="remark" type="text" style="width:300px">
<br><br>
<a id="btn1" href="#">确认</a>    
<a id="btn3" href="#">取消</a>    
</div>  
</form>
</div>  

<!-- 修改界面 -->
<div id="win1">
<form id="ff1" method="post"> 
<div style="text-align: center;padding-top: 50px;">
姓名：<input id="name1"  name="name1" type="text" style="width:300px">
<br><br>
电话：<input id="phone1" name="phone1" type="text" style="width:300px">
<input type="hidden" id="id1" name="id1" >
<br><br>
备注：<input id="remark1" name="remark1" type="text" style="width:300px">
<br><br>
<a id="btn4" href="#">确认</a>    
<a id="btn5" href="#">取消</a>    
</div>  
</form>
</div>  

<!-- Excel导入界面 -->
<div id="win2">
<form id="ff2"   enctype="multipart/form-data"  method="post"> 
<div style="text-align: center;padding-top: 50px;" >
上传文件1：<input id="fb" type="text" name="file1" style="width:300px">
<br><br>
*表格列名请严格按照姓名，身份证号，电话，备注的顺序。
<br><br>
<a id="btn6" href="#">确认</a>    
<a id="btn7" href="#">取消</a>    
</div>  
</form>
</div> 

    </div>   
</body>
<script type="text/javascript">
$(function(){
	
	//1，3新增按钮
	$('#btn1').linkbutton({ 
		iconCls: 'icon-ok',
		onClick:function(){
			$('#ff').form('submit');
		}
	});
	$('#btn3').linkbutton({   
		iconCls: 'icon-no',
		onClick:function(){
			 $('#name').textbox('clear');
		       $('#id').textbox('clear');
		     $('#phone').textbox('clear');
		     $('#remark').textbox('clear');
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
	//6,7导入Excel按钮
	$('#btn6').linkbutton({ 
		iconCls: 'icon-ok',
		onClick:function(){
			$('#ff2').form('submit');
		}
	});
	$('#btn7').linkbutton({   
		iconCls: 'icon-no',
		onClick:function(){
			$('#win2').window("close",true);
		}
	});  
	//添加表单
	$('#ff').form({    
	    url:'insertOwnerinfo',    
	    onSubmit: function(){    
	        var name = $('#name').val();
	        var id = $('#id').val();
	        var phone = $('#phone').val();
	        if(name.length==0||id.length==0||phone.length==0)
	        	{
	        	$.messager.alert('错误', '姓名/身份证号/电话有一项为空', 'error');
	        	return false;
	        	}
	        else if(!(isNaN(name)))
	        	{
	        	$.messager.alert('错误', '格式错误：姓名为数字', 'error');
	        	return false;
	        	}
	        else if(id.length!=18)
	        	{
	        	$.messager.alert('错误', '格式错误：身份证长度错误', 'error');
	        	return false;
	        	}
	        else if((isNaN(phone)))
	        	{
	        	$.messager.alert('错误', '格式错误：电话不为数字', 'error');
	        	return false;
	        	}
	        else if(phone.length!=11&&phone.length!=8)
	        	{
	        	$.messager.alert('错误', '格式错误：电话长度错误', 'error');
	        	return false;
	        	}
	    },    
	    success:function(data){    
	        var s = eval("("+data+")");
	        if(s.state=='1')
	        	{
	        	$.messager.alert('成功', '插入业主信息成功', 'info');
	        	$('#win').window("close",true);
	        	 $('#name').textbox('clear');
			       $('#id').textbox('clear');
			     $('#phone').textbox('clear');
			     $('#remark').textbox('clear');
	        	$('#dg').datagrid('load');
	        	}
	        else
	        	{
	        	$.messager.alert('错误', '插入业主信息失败', 'error');
	        	}
	    }    
	});
	//修改表单
	$('#ff1').form({    
	    url:'updateOwnerinfo',    
	    onSubmit: function(){    
	        var name = $('#name1').val();
	        var phone = $('#phone1').val();
	        if(name.length==0||phone.length==0)
	        	{
	        	$.messager.alert('错误', '姓名/电话有一项为空', 'error');
	        	return false;
	        	}
	        else if(!(isNaN(name)))
	        	{
		        	$.messager.alert('错误', '格式错误：姓名为数字', 'error');
		        	return false;
		        	}
	        else if((isNaN(phone)))
        	{
        	$.messager.alert('错误', '格式错误：电话不为数字', 'error');
        	return false;
        	}
           else if(phone.length!=11&&phone.length!=8)
        	{
        	$.messager.alert('错误', '格式错误：电话长度错误', 'error');
        	return false;
        	}
	    },    
	    success:function(data){    
	        var s = eval("("+data+")");
	        if(s.state=='1')
	        	{
	        	$.messager.alert('成功', '修改业主信息成功', 'info');
	        	$('#win1').window("close",true);
	        	$('#dg').datagrid('load');
	        	}
	        else
	        	{
	        	$.messager.alert('错误', '修改业主信息失败', 'error');
	        	}
	    }    
	});    
	//导入Excel表单
	$('#ff2').form({    
	    url:'addExcelOwnerinfo',    
	    onSubmit: function(){    
	       var name = $("#fb").filebox('getValue');
	       if (name.indexOf(".xlsx") >= 0||name.indexOf(".xls") >= 0) {  
	       }  
	       else
	    	   {
	    	   $.messager.alert('错误', '所选中文件不是Excel文件', 'error');
	    	   $("#fb").filebox('clear');
	    	   return false;
	    	   }
	       
	    },    
	    success:function(data){    
	        var s = eval("("+data+")");
	        if(s.state)
  	        	
	        	{
	        	$.messager.alert('成功', '添加业主信息成功', 'info');
	        	$('#win2').window("close",true);
	        	$('#dg').datagrid('load');
	        	}
	        else
	        	{
	        	$.messager.alert('错误', '添加业主信息失败', 'error');
	        	}
      }
	});   

	
	$('#name').textbox({    
		width:200,
		height:40,
		prompt:"请输入业主姓名",
		type:"text",
		iconCls:'icon-man', 
		iconAlign:'left'       
	})
		$('#id').textbox({    
		width:200,
		height:40,
		prompt:"请输入身份证号",
		type:"text",
		iconCls:'icon-man', 
		iconAlign:'left'       
	})
		$('#phone').textbox({    
		width:200,
		height:40,
		prompt:"请输入电话",
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
	
	$('#name1').textbox({    
		width:200,
		height:40,
		prompt:"请输入业主姓名",
		type:"text",
		iconCls:'icon-man', 
		iconAlign:'left'       
	})
		$('#phone1').textbox({    
		width:200,
		height:40,
		prompt:"请输入电话",
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
	$('#fb').filebox({    
    buttonText: '选择文件', 
    buttonAlign: 'left' 
})


	$('#dg').datagrid({    
	    url:'showOwnerinfo',    
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
	        {field:'o_ownerId',title:'业主编号',width:100},   
	        {field:'o_name',title:'姓名',width:100},    
	        {field:'o_id',title:'身份证号',width:100},    
	        {field:'o_phone',title:'电话',width:100},
	        {field:'o_state',title:'状态',width:100}, 
	        {field:'o_remark',title:'备注',width:100}, 
	    ]],
	    toolbar:[{
            text:'添加业主信息',
            iconCls:'icon-add',
            handler:function(){
            	$('#win').window("open",true);
            }
        },'-',{
            text:'导入Excel文件',
            iconCls:'icon-add',
            handler:function(){
            	$('#win2').window("open",true);
            }
        },'-',
        {
            text:'修改业主信息',
            iconCls:'icon-cut',
            handler:function(){
            	var checks =  $('#dg').datagrid('getSelections');
            	if(checks.length==1)
            		{
            		$('#name1').textbox('setValue',checks[0].o_name);
            		$('#id1').attr('value',checks[0].o_id);
            		$('#phone1').textbox('setValue',checks[0].o_phone);
            		$('#remark1').textbox('setValue',checks[0].o_remark);
            		$('#win1').window("open",true);
            		}
            }
        },'-',{
            text:'移除业主信息',
            iconCls:'icon-save',
            handler:function(){
            	var checks =  $('#dg').datagrid('getSelections');
            	var array = new Array();
            	for (var i = 0; i < checks.length; i++) {
					array.push(checks[i].o_id);
				}
            	if(checks.length!='0')
            		{
            		 $.ajax({
                          url:'removeOwnerinfo',
                          type:'post',
                          data:{'ids':array},
                          success:function(data){	
                        	  var s = eval("("+data+")");
                  	        if(s.state=='1')
                  	        	
                  	        	{
                  	        	$.messager.alert('成功', '移除业主信息成功', 'info');
                  	        	$('#dg').datagrid('load');
                  	        	}
                  	        else
                  	        	{
                  	        	$.messager.alert('错误', '移除业主信息失败', 'error');
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