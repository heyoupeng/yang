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
	$('#tt').tree({
	    url:'getRoleFunctions?rid=${sessionScope.user.rid}',
	    onClick:function(node){
	    	if(node.attributes.fpath){
	    		if($('#tabs').tabs('exists',node.text)){
	    			$('#tabs').tabs('select',node.text)
	    		}
	    		else{
	    		$('#tabs').tabs('add',{
	    			title: node.text,
	    			closable: true,
	    			content:"<iframe  width='100%' height='100%' frameborder='0' src='"+node.attributes.fpath+"'></iframe>"
	    		});
	    		}
	    	}
	    },
	    onLoadSuccess:function(node,data){
	    	$("#tt").tree('expandAll');
	    }
	});     
})
</script>
<body>
<body class="easyui-layout">
	<div data-options="region:'north',title:'物业管理系统',split:true" style="height:100px;"></div>   
    <div data-options="region:'west',title:'欢迎',split:true" style="width:300px;">
    	<ul id="tt"></ul>  
    </div>   
    <div data-options="region:'center',border:false" style="padding:5px;background:#eee;">
    	<div id="tabs" class="easyui-tabs" data-options="fit:true,border:false"> 
    		<div title="欢迎界面" style="padding:20px;display:none;">  
    			<iframe  width='100%' height='100%' frameborder='0' src='welcome.jsp'></iframe>
    		</div>
    	</div>
    </div>     
</body>
</body>
</html>