<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>视频加载失败</title>
    <style>
    body{background: #ffffff;}
    	*{margin: 0px;padding: 0px;}
    	#div1{width:429px;height:140px;
    	position:absolute; top:30%;
    	margin-left:500px;
    	text-align:center;
    	background: #fff;}
    	p{margin-top: 30px;font-size: 13px;font-family: "微软雅黑";color:red;margin-bottom: 15px;}
    	a{text-decoration: none; color: blue;}
    	a:hover{cursor: pointer;color: orange;font-weight: bold;}
    	#div2{margin-top: 10px;};
    </style>
  </head>
  <body>
  	<div id="div1">
  		<p>视频加载失败,可能原因：1视频不存在，2网速不够</p>
  		<a href="homeAction_list.action">首页</a>
  		<a onclick="javascript:window.history.back(-1);">返回上一页</a>
  		<div id="div2">联系管理员</div>
  	</div>
    
  </body>
</html>
