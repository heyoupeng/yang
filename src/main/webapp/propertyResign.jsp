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
	 $.ajax({
		url:'judgePropertyResign',
		type:'post',
		success:function(data)
		{
			var data = eval("("+data+")");
			if(data.state=='0')
				{
			$('#win').window("open",true);
				}
			else
				{
				$('#div2').append("<div style='position: relative;right:8px;'>开始时间：<input id='starttime'  name='starttime'  type='text' style='width:300px'></div><br>")
				$('#starttime').textbox({    
							width:200,
							height:40,
							editable:false,
							type:"text",
							iconAlign:'left'       
										})
				$('#div2').append("<div style='position: relative;left:5px;'>状态：<input id='state'  name='state'  type='text' style='width:300px'></div><br>")
				$('#state').textbox({    
								width:200,
								height:40,
								editable:false,
								type:"text",
								iconAlign:'left'       
									})
				if(data.state[3]=='未审批')
					{
					$("#btn1").empty();
					$('#content').textbox('setValue',data.state[0]);
					$('#content').textbox('readonly');
					$('#starttime').textbox('setValue',data.state[1]);
					$('#state').textbox('setValue',data.state[3]);
					$('#win').window("open",true);
					}
				else
					{
					$("#btn1").empty();
					$('#content').textbox('setValue',data.state[0]);
					$('#content').textbox('readonly');
					$('#starttime').textbox('setValue',data.state[1]);
					$('#state').textbox('setValue',data.state[3].substring(0,8));
					$('#div2').append("<div style='position: relative;right:8px;'>审批时间：<input id='endtime'  name='endtime'  type='text' style='width:300px'></div><br>")
					$('#endtime').textbox({    
								width:200,
								height:40,
							  	editable:false,
								type:"text",
								iconAlign:'left'       
											})
					$('#endtime').textbox('setValue',data.state[2]);
					$('#win').window("open",true);
					}
				}
			
		}
	 });
	$('#win').window({   
	    width:600,    
	    height:400,    
	    modal:true,
	    closed:true,
	    title:"辞职申请书",
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
    <div  data-options="region:'center',title:'填写辞职申请'" style="padding:5px;background:#eee;">
    <div id="win">
    <form id="ff" method="post"> 
<div id="div2" style="text-align: center;padding-top: 50px;">
申请人：<input id="num"  name="num" value="本人" type="text" style="width:300px">
<br><br>
<div style="position: relative;right:8px;">
申请原因：<input id="content" name="content" type="text" style="width:300px">
</div>
<br>
<a id="btn1" href="#">确认</a>    
</div>  
</form>
</div>
    </div>  
</body>  
<script type="text/javascript">
$(function(){
	$('#num').textbox({    
		width:200,
		height:40,
		editable:false,
		type:"text",
		iconCls:'icon-man', 
		iconAlign:'left'       
	})
	
	$('#content').textbox({    
		width:200,
		height:100,
		multiline:true,
		type:"text",
		iconAlign:'left'       
	})
	
	$('#btn1').linkbutton({ 
		iconCls: 'icon-ok',
		onClick:function(){
			$.messager.confirm('确认', '确认提交辞职申请？请慎重考虑。', function(r){
	    		if (r){
	    			$('#ff').form('submit');
	    		}
	    	});
			
		}
	});
	
	$('#ff').form({    
	    url:'propertyResign',    
	    onSubmit: function(){
	    	var content = $('#content').textbox('getValue');
	    	if(content.length==0)
	    		{
	    		$.messager.alert('错误', '内容不能为空', 'error');
	    		return false;
	    		}
	    },    
	    success:function(data){   
	    	 var s = eval("("+data+")");
		        if(s.state=='1')
		        	{
		        	$.messager.alert('成功', '提交辞职申请成功', 'info');
		        	window.location.reload();	
		        	}
		        else
		        	{
		        	$.messager.alert('错误', '提交辞职申请失败', 'error');
		        	}
	    }
	});
	
	
});
</script>
</html>