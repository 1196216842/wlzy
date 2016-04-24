<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>用户注册</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/user_addUI.css" />
</head>
<body>
<div id="list">
		<div class="top"><jsp:include page="/top.jsp"></jsp:include></div>
		<div class="nav">
			<a href="#">当前位置：</a>
			<a href="homeAction_list.action">首页<img
				src="${pageContext.request.contextPath }/images/arrow_close.gif" /></a> 
			<a href="userAction_registerUI.action">用户注册</a>
		</div>
		<div class="content">
			<div id="addUser">
					<p class="text">用户注册</p>
					<div id="style">
						<form action="userAction_register.action" method="post">
						<s:hidden name="userType" value="0"></s:hidden>
						<div style="color:red;">${requestScope.message}</div>
						<div title="只能输入英文或数字">账号&nbsp;<input type="text" name="username" class="txt" onkeyup="value=value.replace(/[^\a-\z\A-\Z0-9]/g,'')" onpaste="value=value.replace(/[^\a-\z\A-\Z0-9]/g,'')" oncontextmenu = "value=value.replace(/[^\a-\z\A-\Z0-9]/g,'')"/></div>
						<div title="只能输入英文或数字">密码&nbsp;<input type="password" name="password" class="txt"/ onkeyup="value=value.replace(/[^\a-\z\A-\Z0-9]/g,'')" onpaste="value=value.replace(/[^\a-\z\A-\Z0-9]/g,'')" oncontextmenu = "value=value.replace(/[^\a-\z\A-\Z0-9]/g,'')"></div>
						<div>姓名&nbsp;<input type="text" name="name" class="txt"/></div>
						<div>性别&nbsp;<input type="text" name="gender" class="txt"/></div>
						<div>电话&nbsp;<input type="text" name="phoneNumber" class="txt"/></div>
						<div>邮箱&nbsp;<input type="text" name="email" class="txt"/></div>
						<div>简介&nbsp;<textarea name="description" class="txt"/></textarea></div>
						<div class="but"><input type="submit" type="button" value="注&nbsp;&nbsp测"/></div>
						</form>
					</div>
			</div>
		</div>
</div>
</body>
</html>
