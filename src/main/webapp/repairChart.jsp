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
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
</head>
<script type="text/javascript">
$(function(){
	$('#cc').combobox({    
	    valueField:'id',    
	    textField:'text' ,
	    panelHeight:70,
		editable:false,
		data:[
		      {
		    	  id:'2016',
		    	  text:'2016'
		      },
		      {
		    	  id:'2017',
		    	  text:'2017'
		      }
		      ]
	});  
	$('#ff').form({    
	    url:'repairChart',    
	    onSubmit: function(){    
	    	 var year = $('#cc').combobox('getValue');
		        if(year.length==0)
		        	{
		        	$.messager.alert('错误', '请选择年份', 'error');
		        	return false;
		        	}    
	    },    
	    success:function(data){   
	    	  $('#myChart').remove();
	    	  $('#div2').append('<canvas  id="myChart" ></canvas>');
	          var data = eval("("+data+")");
	          var ctx = document.getElementById("myChart").getContext("2d");
	          var chart = new Chart(ctx, {
	        	    type: 'bar',
	        	    data: data,
	        	    options: {
	        	        title: {
	        	            display: true,
	        	            text: '维修人员每月工单'
	        	        }
	        	    },
	        	    
	        	})
	    }    
	});    
	$('#btn').linkbutton({    
	    iconCls: 'icon-search',
	    onClick:function(){
	    	$('#ff').form('submit');
	    }
	});  
});
</script>
<body class="easyui-layout">   
    <div data-options="region:'center',title:'报表显示	'" style="padding:5px;background:#eee;">
     <form id="ff" method="post">   
        请选择年份：<input id="cc" name="year"/>
    <a id="btn" href="#">确认</a> 
    </form>    
      <div id="div2" style="width: 800px;height:400px">
        <canvas id="myChart" ></canvas>
      </div>
    </div>   
</body>  
</html>