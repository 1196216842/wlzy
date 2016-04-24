<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>添加用户</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/user_addUI.css" />
</head>
<body>
<div id="list">
		<div class="top"><jsp:include page="/top.jsp"></jsp:include></div>
		<div class="nav">
			<a href="#">当前位置：</a>
			<a href="homeAction_list.action">首页<img
				src="${pageContext.request.contextPath }/images/arrow_close.gif" /></a> 
			<a href="userAction_list.action">用户管理<img
				src="${pageContext.request.contextPath }/images/arrow_close.gif" /></a>
			<a href="userAction_addUI.action">添加</a>
		</div>
		<div class="content">
			<div id="addUser">
					<p class="text">添加用户</p>
					<p class="text"><font color="red">${requestScope.message }</font></p>
					<div id="style">
						<form action="userAction_add.action" method="post">
						<!-- 默认密码123 -->
						<input type="hidden" name="password" value="123"/>
						<div title="只能输入英文或数字">账号&nbsp;<input type="text" name="username" class="txt" onkeyup="value=value.replace(/[^\a-\z\A-\Z0-9]/g,'')" onpaste="value=value.replace(/[^\a-\z\A-\Z0-9]/g,'')" oncontextmenu = "value=value.replace(/[^\a-\z\A-\Z0-9]/g,'')"/></div>
						<!-- <div>密码&nbsp;<input type="password" name="password" class="txt"/></div> -->
						<div>姓名&nbsp;<input type="text" name="name" class="txt"/></div>
						<div>性别&nbsp;<input type="text" name="gender" class="txt"/></div>
						<div>电话&nbsp;<input type="text" name="phoneNumber" class="txt"/></div>
						<div>邮箱&nbsp;<input type="text" name="email" class="txt"/></div>
						<div>简介&nbsp;<textarea name="description" class="txt"/></textarea></div>
						<div>类型&nbsp;<input type="text" name="userType" class="txt"/></div>
						<div>(学生：0&nbsp;  老师：1  &nbsp;管理员：2)</div>
						<div class="but"><input type="submit" value="添&nbsp;&nbsp加"/></div>
						</form> 
					</div>
			</div>
		</div>
</div>
</body>
</html>
