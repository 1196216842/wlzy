<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>用户中心</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/user_other.css" />
</head>

<body>
<div id="list">
		<div class="top"><jsp:include page="/top.jsp"></jsp:include></div>
		<div class="nav">
			<a href="#">当前位置：</a>
			<a href="homeAction_list.action">首页<img
				src="${pageContext.request.contextPath }/images/arrow_close.gif" /></a> 
			<a href="userAction_other.action?id=${requestScope.user.id}">用户中心</a>
		</div>
		<div class="content">
			<!-- 用户的信息 -->
			<div id="otherInfo">
				 <table>
						<tr>
							<td>登录名:</td>
							<td  colspan="3">${requestScope.user.username}
							<%--  <s:a action="userAction_addFriend?id=%{#request.user.id}">[加为好友]</s:a> --%>
							</td>
						</tr>
						<tr>
							<td>姓名:</td>
							<td>${requestScope.user.name}</td>
							<td>性别:</td>
							<td>${requestScope.user.gender}</td>
						</tr>
						<tr>
							<td>电话号码:</td>
							<td>${requestScope.user.phoneNumber}</td>
							<td>邮箱:</td>
							<td>${requestScope.user.email}</td>
						</tr>
						<tr>
							<td>个人简介:</td>
							<td colspan="3">${requestScope.user.description}</td>
						</tr>
		  			</table>
	  			</div>
	  			<!-- 头像 -->
	  			<div id="head">
	  				<img src="${pageContext.request.contextPath }/images/user.jpg" />
	  			</div>
	  			<!-- 自己发表的主题 -->
				<div id="otherTopic">
					<div id="divTopic">
					<p>用户的主题</p>
						<ul>
							<s:iterator value="#topics">
								<li>
									<s:a action="forumAction_show?id=%{forum.id}">
										<img src="${pageContext.request.contextPath }/images/topicType_${type}.gif" />[${forum.name}]
									</s:a>
									<s:a action="topicAction_show?id=%{id}" title="%{title}">
										${title}
									</s:a>
								</li>
								<span><fmt:formatDate value="${postTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
							</s:iterator>
						</ul>
					 </div>
				</div>
		</div>
</div>
	<div class="pageRoll5">
		<jsp:include page="/WEB-INF/jsp/home/pageRoll.jsp" flush="true">
			<jsp:param value="userAction_other?id=${requestScope.user.id}" name="action"/>
		</jsp:include>
	</div>
</body>
</html>
