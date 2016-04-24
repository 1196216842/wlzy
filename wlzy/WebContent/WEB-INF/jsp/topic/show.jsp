<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>${requestScope.topic.title}</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/topic_show.css" />
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
			 <a href="forumAction_list.action">论坛天地<img
				src="${pageContext.request.contextPath }/images/arrow_close.gif" />
			</a> 
			<a href="forumAction_show.action?id=${requestScope.topic.forum.id }">${requestScope.topic.forum.name}<img
				src="${pageContext.request.contextPath }/images/arrow_close.gif" />
			</a><a href="topicAction_show.action?id=${topic.id}" title="${ topic.title }">看帖</a><a href="#addUI">【回复】</a>
</div>	
<div class="content">
	<div id="topic">
		<div class="userInfo">
			<img src="${pageContext.request.contextPath }/images/user.jpg" />
			<p>
				<s:a action="userAction_other?id=%{#topic.author.id}">${requestScope.topic.author.username}</s:a>
			</p>
		</div>
		
		<div class="topicInfo">
				<div class="title" title="${ topic.title }">
					<img src="${pageContext.request.contextPath }/images/topicType_${requestScope.topic.type}.gif" />
					<span>${requestScope.topic.title}</span>
				</div>
				<div class="info">
					<pre>${requestScope.topic.content }</pre>
				</div>
		</div>
		
		<div class="operate">
			 <div class="topicType">
						日期:
						<fmt:formatDate value="${requestScope.topic.postTime}"
							pattern="yyyy-MM-dd" />
						<br /> 回复数:&nbsp;${requestScope.topic.replyCount}<br />
						楼层:&nbsp;楼主
			</div>
			<div class="topicTools">
					<a href="#addUI">回复</a><br/>
					<s:if test="#session.user.userType==2">
						<s:a action="topicAction_editUI?id=%{#topic.id}">修改</s:a>
						<s:a action="topicAction_delete?id=%{#topic.id}"
							onClick="return confirm('确定要删除本帖吗？')">删除</s:a>
						<br/>
						<s:a action="topicAction_hot?id=%{#topic.id}"
							onClick="return confirm('要把本主题设为精华吗？')">精华</s:a>
						<s:a action="topicAction_top?id=%{#topic.id}"
							onClick="return confirm('要把本主题设为置顶吗？')">置顶</s:a>
						<s:a action="topicAction_comm?id=%{#topic.id}"
							onClick="return confirm('要把本主题设为普通吗？')">普通</s:a>
						<div id="moveDiv">移动主题</div>
							<div id="move">
								<!--显示表单内容-->
								<s:form action="topicAction_move">
									<s:hidden name="id"></s:hidden>
										<s:select name="forumId" 
												list="#forums" listKey="id" listValue="name"
												headerKey="%{#topic.forum.id}" headerValue="%{#topic.forum.name}">
										</s:select> 
									<input id="moveBtn" type="submit" value="提交"/>
								</s:form>
							</div>
						</s:if>
					</div>
				</div>
	</div>
	<div id="reply">
		<s:iterator value="#replys">
			<div class="reply">
				<div class="userInfo">
				<img src="${pageContext.request.contextPath }/images/user.jpg" />
				<p>
					<s:a action="userAction_other?id=%{author.id}">${author.username}</s:a>
				</p>
			</div>
			
			<div class="replyInfo">
					<div class="title" title="${title }">
						<span>回复:${title}</span>
					</div>
					<div class="info">
						<pre>${content }</pre>
					</div>
			</div>
			
			<div class="operate">
				 <div class="replyType">
							日期:
							<fmt:formatDate value="${postTime}"
								pattern="yyyy-MM-dd" />
							<br />
							楼层:&nbsp;${floor}
				</div>
				<div class="replyTools">
						<s:if test="#session.user.userType==2">
								<s:a action="replyAction_editUI?id=%{id}">修改</s:a>
								<s:a action="replyAction_delete?id=%{id}"
									onClick="return confirm('确定要删除此回复吗？')">删除</s:a>
						</s:if>
						</div>
				</div>
		</div>
		</s:iterator>
	</div> 
</div>
<!-- ------------------------------------------------------------------------------------ -->
	
<div class="pageRoll">
		<jsp:include page="/WEB-INF/jsp/home/pageRoll.jsp" flush="true">
			<jsp:param value="topicAction_show.action?id=${topic.id }" name="action"/>
		</jsp:include>
</div>

<!-- ------------------------------------------------------------------------------------ -->
<!--发表新帖-->
<div id="addUI">
		<!-- 表单区域 -->
		<div id="addForm">
			<form id="form1"  action="replyAction_add.action" method="post">
				<s:hidden name="topicId" value="%{#topic.id}"></s:hidden>
				<s:hidden name="title" value="%{#topic.title}"></s:hidden>
				<p id="show_reply">回复:${requestScope.topic.title}</p>
				<script name="content"   id="editor" type="text/plain" style="width:100%;height:170px;"></script>
			</form>
				<p id="p3"><input id="button" type="button" value="发表"/></p>
		</div>
		<!-- js区域 -->
		<script type="text/javascript" src="${pageContext.request.contextPath }/ueditor/ueditor.config.js"></script>
	 	<script type="text/javascript" src="${pageContext.request.contextPath }/ueditor/ueditor.all.js"></script>
		<script type="text/javascript">
			window.onload=function(){
			 	var ue = UE.getEditor('editor');
				var button=document.getElementById("button");
				button.onclick=function(){
					var username = '${sessionScope.user.username}';
					if (username== "") {
						alert("请登录后再操作!");
						return false;
					}
					else{
						document.getElementById("form1").submit();
					}
				}; 
			};
		</script>
</div> 
	<!-- ----------------------------------------------------------------------------- -->
<div class="bottom">
<jsp:include page="/bottom2.jsp"></jsp:include>
</div>
</body>
</html>