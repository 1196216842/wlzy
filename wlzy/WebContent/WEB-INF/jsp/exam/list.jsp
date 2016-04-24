<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>试题</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/exam_list.css" />
</head>
<body>
	<div id="list">
		<div class="top"><jsp:include page="/top.jsp"></jsp:include></div>
		<div class="nav">
			<a href="#">当前位置：</a>
			<a href="homeAction_list.action">首页<img
				src="${pageContext.request.contextPath }/images/arrow_close.gif" />
			</a> <a href="examTopicAction_list.action">在线测试<img
				src="${pageContext.request.contextPath }/images/arrow_close.gif" />
			</a><a>试题</a>
			<s:if test="#session.user.userType==1||#session.user.userType==2">
				<a style="float:right;margin-right: 10px;" href="examTopicAction_uploadUI.action">上传试题</a>
			</s:if>
		</div>
		<div class="content">
			<div id="exam">
				<s:iterator value="examTopics">
					<div class="exams"><a href="examOptionAction_show.action?examTopicId=${id}">${name}</a></div>
				</s:iterator>
			</div>
		</div>
	</div>
</body>
</html>
