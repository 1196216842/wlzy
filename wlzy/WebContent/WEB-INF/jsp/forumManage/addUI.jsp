<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>添加版块</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/forumManage_addUI.css" />
</head>
<body>
<div id="list">
		<div class="top"><jsp:include page="/top.jsp"></jsp:include></div>
		<div class="nav">
			<a href="#">当前位置：</a>
			<a href="homeAction_list.action">首页<img
				src="${pageContext.request.contextPath }/images/arrow_close.gif" /></a> 
			<a href="forumManageAction_list.action">版块管理<img
				src="${pageContext.request.contextPath }/images/arrow_close.gif" /></a>
			<a href="forumManageAction_addUI.action">添加</a>
		</div>
		<div class="content">
			<div id="addForum">
					<p id="text">添加版块</p>
					<div id="style">
					<s:form action="forumManageAction_add.action" method="post">
						<div>版块名称&nbsp;<input type="text" name="name" class="txt1"/>*</div>
						<div>版块说明&nbsp;<textarea name="description" class="txt2"></textarea></div>
						<div><input type="submit" value="确定" class="txt3"/></div>
					</s:form>
				</div>
			</div>
		</div>
</div>
</body>
</html>
