<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>个人中心</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/user_self.css" />
</head>

<body>
<div id="list">
		<div class="top"><jsp:include page="/top.jsp"></jsp:include></div>
		<div class="nav">
			<a href="#">当前位置：</a>
			<a href="homeAction_list.action">首页<img
				src="${pageContext.request.contextPath }/images/arrow_close.gif" /></a> 
			<a href="userAction_self.action">个人中心</a>
			<a style="float: right;margin-right: 10px;" href="userAction_initPassword.action" onClick="return confirm('确定要初始化密码为123么？')">初始化密码</a>
			<a style="float: right;" href="userAction_changePasswordUI.action">修改密码&nbsp;|</a>
			<a style="float: right;" href="userAction_selfEditUI.action">修改&nbsp;|</a>
		</div>
		<div class="content">
			<!-- 自己的信息 -->
			<div id="selfInfo">
				 <table>
						<tr>
							<td>登录名:</td>
							<td  colspan="3">${sessionScope.user.username}
							</td>
						</tr>
						<tr>
							<td>姓名:</td>
							<td>${sessionScope.user.name}</td>
							<td>性别:</td>
							<td>${sessionScope.user.gender}</td>
						</tr>
						<tr>
							<td>电话号码:</td>
							<td>${sessionScope.user.phoneNumber}</td>
							<td>邮箱:</td>
							<td>${sessionScope.user.email}</td>
						</tr>
						<tr>
							<td>个人简介:</td>
							<td colspan="3">${sessionScope.user.description}</td>
						</tr>
		  			</table>
	  			</div>
	  			<!-- 头像 -->
	  			<div id="head">
	  				<img src="${pageContext.request.contextPath }/images/user.jpg" />
	  			</div>
	  			<!-- 自己发表的主题 -->
				<div id="selfTopic">
					<div id="divTopic">
					<p>我的主题</p>
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
	<div class="pageRoll4">
		<jsp:include page="/WEB-INF/jsp/home/pageRoll.jsp" flush="true">
			<jsp:param value="userAction_self" name="action"/>
		</jsp:include>
	</div>
</body>
</html>
