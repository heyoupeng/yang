<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%> 
	 
<%@ page import="java.sql.*"%>   
<%@ page import="com.yh.listener.SessionCounter" %>  
<!DOCTYPE html>
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
<title>ChattingRoom</title>
<script>
$(function(){
	//配置提交按钮
	$('#text').textbox({    
	    //buttonText:'用户名',    
	    iconAlign:'right',
	    prompt:'输入聊天信息',
	    width:'100%',
	    height:'100%',
	});
	//配置发送按钮
	$("#send").linkbutton({
		iconCls: 'icon-ok',
	    onClick:function(){
	    	send();
	    	$("#text").textbox("clear");
	    }
	});
	$("#close").linkbutton({
		iconCls: 'icon-no',
	    onClick:function(){
	    	
	    }
	});
	//----------------------------------------键盘监听器
	$(window).keydown(function(event) {
			//alert(event.keyCode);
			//console.log(event.keyCode)
		switch (event.keyCode) {
			
		case 13:
			$("#send").click();
		}
	});
});
</script>
</head>
<body class="easyui-layout" > 
    <div data-options="region:'south',title:'消息发送',split:true" style="height:200px;font-size:20px;color:red">
    		<input id="text" class="easyui-textbox" data-options="multiline:true">
		<div id="btn" style="position:fixed;bottom:10px;right:20px">
			<button id="send">发送消息</button>&nbsp;&nbsp;
			<button id="close" onclick="closeWebSocket()">关闭连接</button>
		</div>	
    </div>     
    <div data-options="region:'west',title:'线上',split:true" style="width:100px;">
    	目前: <span id="count"><%=SessionCounter.getActiveSessions()%> 人在线</span> <br/>
    </div>   
    <div data-options="region:'center',title:'欢迎进入聊天室'" style="background:#fff;">
    	<div id="message"></div>
    </div> 
	
</body>
<script type="text/javascript">
	var websocket = null;
	//判断当前浏览器是否支持WebSocket
	if ('WebSocket' in window) {
		websocket = new WebSocket("ws://172.18.23.27:8080/yang/websocket");
	} else {
		alert('当前浏览器 Not support websocket!');
	}
	//连接发生错误的回调方法
	websocket.onerror = function() {
		setMessageInnerHTML("WebSocket连接发生错误!");
	};
	//连接成功建立的回调方法
	websocket.onopen = function() {
		setMessageInnerHTML("WebSocket连接成功!");
	}
	//接收到消息的回调方法
	websocket.onmessage = function(event) {
		setMessageInnerHTML(event.data);
	}
	//连接关闭的回调方法
	websocket.onclose = function() {
		setMessageInnerHTML("WebSocket连接关闭!");
	}
	//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
	window.onbeforeunload = function() {
		closeWebSocket();
	}
	//将消息显示在网页上
	function setMessageInnerHTML(innerHTML) {
		document.getElementById('message').innerHTML += innerHTML + '<br/>';
	}
	//关闭WebSocket连接
	function closeWebSocket() {
		websocket.close();
	}
	//发送消息
	function send() {
		var message = document.getElementById('text').value;
		if(message!="")
		{
			websocket.send("${IP}"+":  "+message);
		}
	}
	//------------------------------------------------------------------显示在线人数
	$(document).ready(function () {
            // 创建链接
            var socket = io.connect('http://172.18.23.27:8080/yang/websocket');
             socket.on('stats', function(data) {
             console.log('Connected clients:', data.numClients);
             $('#count').text(data.numClients);
         });
     });
	var express = require('express');
	var router = express.Router();
	var path = require('path');
	var WebSocket = require('ws');
	var WebSocketServer = WebSocket.Server;
	var io = new WebSocketServer({
	    port: 8080
	});
	var numClients = 0;
	//统计人数
	io.on('connection', function(socket) {  
	        numClients++;
	        socket.emit('stats', { numClients: numClients });
	        console.log('Connected clients:', numClients);
	        socket.on('disconnect', function() {
	        numClients--;
	        socket.emit('stats', { numClients: numClients });
	        console.log('Connected clients:', numClients);
	    });
	});
</script>

</html>