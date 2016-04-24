<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>用户管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/user_list.css" />
</head>

<body>

	<div id="list">
		<div class="top"><jsp:include page="/top.jsp"></jsp:include></div>
		<div class="nav">
			<a href="#">当前位置：</a>
			<a href="homeAction_list.action">首页<img
				src="${pageContext.request.contextPath }/images/arrow_close.gif" /></a> 
			<a href="userAction_list.action">用户管理</a>
			<a style="float: right;margin-right: 20px;" href="userAction_addUI.action">添加</a>
		</div>
		<div class="content">
			<div id="userList">
			<table>
			<!--表头-->
			<tr id="tr1">
				<td>ID</td>
				<td>用户类型</td>
				<td>登录名</td>
				<!-- <td>密码</td> -->
				<td>姓名</td>
				<td>性别</td>
				<td>电话号码</td>
				<td>邮箱</td>
				<td>个人简介</td>
				<td>相关操作</td>
			</tr>
				<!--列表-->
		<s:iterator value="#users">
			<tr>
				<td>${id}</td>
				<td>
					<s:if test="userType==0">
							学生
					</s:if>
					<s:if test="userType==1">
							老师
					</s:if>
					<s:if test="userType==2">
							管理员
					</s:if>
					</td>
					<td>${username}</td>
					<%-- <td>${password}</td> --%>
					<td>${name}</td>
					<td>${gender}</td>
					<td>${phoneNumber}</td>
					<td>${email}</td>
					<td>${description}</td>
					<td>
					<s:a action="userAction_delete?id=%{id}" onClick="return confirm('确定要删除该用户么？')">删除</s:a>
					<s:a action="userAction_editUI?id=%{id}">修改</s:a>
					<s:a action="userAction_initialization?id=%{id}" onClick="return confirm('确定要初始化密码为123么？')">初始化密码</s:a>
					</td>
				</tr>
			</s:iterator>
			</table>
		</div>
	 </div>
	</div>
	<div class="pageRoll3">
		<jsp:include page="/WEB-INF/jsp/home/pageRoll.jsp" flush="true">
			<jsp:param value="userAction_list" name="action"/>
		</jsp:include>
	</div>
</body>
</html>
