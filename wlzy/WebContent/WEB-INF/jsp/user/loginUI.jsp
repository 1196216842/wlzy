<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>登录</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/login.css" />
</head>
<body>
<div id="list">
		<div class="top"><jsp:include page="/top.jsp"></jsp:include></div>
		<div class="nav">
			<a href="#">当前位置：</a>
			<a href="homeAction_list.action">首页<img
				src="${pageContext.request.contextPath }/images/arrow_close.gif" /></a> 
			<a href="userAction_loginUI.action">登录</a>
		</div>
		<div class="content">
			<p id="loginForm">登录</p>
			<p style="text-align: center;"><font color="red">${requestScope.message }</font></p>
			<div class="loginForm">
			<form action="userAction_login.action" method="post">
			<p>账号</p><p><input type="text" name="username" class="txt"/></p>
			<p>密码</p><p><input type="password" name="password" class="txt"/></p>
			<p id="radio">
			<label><input class="radio" type="radio" name="userType" value="0"/>学生</label>
			<label><input class="radio" type="radio" name="userType" value="1"/>老师</label>
			<label><input class="radio" type="radio" name="userType" value="2"/>管理员</label>
			</p>		
			<p class="but"><input type="submit" type="button" value="登&nbsp;&nbsp录"/></p>
			</form>
			</div>
		</div>
</div>
</body>
</html>
