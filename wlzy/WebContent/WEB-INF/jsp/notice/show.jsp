<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>${requestScope.notice.title}</title>
<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath }/css/notice_show.css" />
</head>

<body>
<div class="top">
	<jsp:include page="/top2.jsp"></jsp:include>
</div>
<div class="nav">
	<a href="#">当前位置：</a>
			<a href="homeAction_list.action">首页<img
					src="${pageContext.request.contextPath }/images/arrow_close.gif" />
				</a> 
				 <a href="myFileAction_noticeList.action?userId=0">教学中心<img
				src="${pageContext.request.contextPath }/images/arrow_close.gif" />
			</a> 
			<a href="#">通知</a>
			<s:if test="#session.user.userType==1 or #session.user.userType==2">
						<a href="noticeAction_addUI.action?userId=${requestScope.userId }">[发布]</a>
			</s:if>
			<s:if test="#session.user.userType==2">
					<a href="noticeAction_editUI.action?id=${requestScope.notice.id}">[修改]</a>
					<a  href="noticeAction_delete.action?id=${requestScope.notice.id}&userId=${requestScope.userId}" onClick="return confirm('确定要删除这条通知？')">[删除]</a>
			</s:if>
</div>
<div class="content">
		<div id="notice_left">
			<div id="notice_title">${notice.title}</div>
			<div id="notice_username">
				作者:<a href="userAction_other.action?id=${notice.user.id }">${notice.user.name}</a>
			</div>
			<div id="notice_time">
				<fmt:formatDate value="${notice.postTime }"
					pattern="yyyy-MM-dd HH:mm:ss" />
				<s:if test="#notice.type==0">
					<span style="color: blue;">【普通】</span>
				</s:if>
				<s:if test="#notice.type==1">
					<span style="color: orange;">【重要】</span>
				</s:if>
				<s:if test="#notice.type==2">
					<span style="color: red;">【紧急】</span>
				</s:if>
			</div>
			<div id="notice_content">
			<pre>${notice.content}</pre>
			</div>
		</div>
		<div id="notice_right">
			<div id="newNotice">最新通知</div>
					<s:iterator value="#notices">
							<div class="notice">
								<span class="type">
									<s:if test="type==0">
										<span style="color:blue;">【普通】</span>
									</s:if>
									<s:if test="type==1">
										<span style="color:orange;">【重要】</span>
									</s:if>
									<s:if test="type==2">
										<span style="color:red;">【紧急】</span>
									</s:if>
								</span>
								<span class="name"><a href="userAction_other.action?id=${user.id}">${user.name}</a></span>
								<a class="title" title="${title}" href="noticeAction_show.action?id=${id}">${title}</a>
							</div>
						</s:iterator>
				</div>
		</div>
<div class="bottom">
<jsp:include page="/bottom2.jsp"></jsp:include>
</div>
</body>
</html>
