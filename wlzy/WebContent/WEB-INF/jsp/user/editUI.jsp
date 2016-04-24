<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>修改用户</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.js"></script>
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
			<a href="userAction_editUI.action?id=${resquestScope.user.id}">修改</a>
		</div>
		<div class="content">
			<div id="addUser">
					<p class="text">修改用户</p>
					<p class="text"><font color="red">${requestScope.message }</font></p>
					<div id="style">
						<form action="userAction_edit.action" method="post">
						<input type="hidden" name="id" value="${requestScope.user.id}" />
						<div>账号&nbsp;<input type="text" name="username" class="txt" value="${requestScope.user.username}"/></div>
						<%-- <div>密码&nbsp;<input type="password" name="password" class="txt" value="${requestScope.user.password}"/></div> --%>
						<div>姓名&nbsp;<input type="text" name="name" class="txt" value="${requestScope.user.name}"/></div>
						<div>性别&nbsp;<input type="text" name="gender" class="txt" value="${requestScope.user.gender}"/></div>
						<div>电话&nbsp;<input type="text" name="phoneNumber" class="txt" value="${requestScope.user.phoneNumber}"/></div>
						<div>邮箱&nbsp;<input type="text" name="email" class="txt" value="${requestScope.user.email}"/></div>
						<div>简介&nbsp;<textarea  name="description" class="txt"/>${requestScope.user.description}</textarea></div>
						<div>类型&nbsp;<input type="text" name="userType" class="txt" value="${requestScope.user.userType}"/></div>
						<div>(学生：0&nbsp;  老师：1  &nbsp;管理员：2)</div>
						<div class="but"><input type="submit" type="button" value="修&nbsp;&nbsp改"/></div>
						</form>
						
					</div>
			</div>
		</div>
</div>

</body>
</html>
