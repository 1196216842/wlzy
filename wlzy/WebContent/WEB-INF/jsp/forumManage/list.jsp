<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>版块管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/forumManage_list.css" />
<style type="text/css">
</style>
</head>

<body>
	<div id="list">
		<div class="top"><jsp:include page="/top.jsp"></jsp:include></div>
		<div class="nav">
			<a href="#">当前位置：</a>
			<a href="homeAction_list.action">首页<img
				src="${pageContext.request.contextPath }/images/arrow_close.gif" /></a> 
			<a href="userManageAction_list.action">版块管理</a>
			<a style="float: right;margin-right: 20px;" href="forumManageAction_addUI.action">添加</a>
		</div>
		<div class="content">
			<table>
		<tr id="tr1">
			<td>版块名称</td>
	        <td>版块说明</td>
	        <td>相关操作</td>
		</tr>
		<s:iterator value="#forums" status="status">
			<tr>
				<td>${name}</td>
				<td>${description}</td>
				<td><s:a action="forumManageAction_delete?id=%{id}" onClick="return confirm('确定要删除该板块？')">删除</s:a>
					<s:a action="forumManageAction_editUI?id=%{id}">修改</s:a>
					<s:if test="#status.first">
						<span>上移</span>
					</s:if>
					<s:else>
						<s:a action="forumManageAction_moveUp?id=%{id}">上移</s:a>
					</s:else>
					<s:if test="#status.last">
						<span>下移</span>
					</s:if>
					<s:else>
						<s:a action="forumManageAction_moveDown?id=%{id}">下移</s:a>
					</s:else>
				</td>
			</tr>
			</s:iterator>
		</table>
	 </div>
	</div>
</body>
</html>
