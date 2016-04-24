<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>${requestScope.forum.name}</title>
<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath }/css/forum_show.css" />
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
				src="${pageContext.request.contextPath }/images/arrow_close.gif"/>
		</a>
		<a href="forumAction_show.action?id=${forum.id }">${requestScope.forum.name}
		</a>
		<a id="newTopic" href="#addUI">【新帖】</a>
</div>	
<div class="content">
		<s:iterator value="#topics">
				<div class="topic">
					<div class="topic_title">
						<img src="${pageContext.request.contextPath }/images/topicType_${type}.gif" />
						<s:a  action="topicAction_show?id=%{id}">${title}</s:a>
					</div>
					<div class="topic_content">${content }</div>
					<div class="topic_info">
						<p>发表时间:<fmt:formatDate value="${postTime}" pattern="yyyy-MM-dd" /></p>
						<p>回复数:${replyCount}</p>
						<p>作者:${author.username}</p>
					</div>
				</div>
		</s:iterator>
</div>
<!-- ------------------------------------------------------------------------------------ -->
	
<div class="pageRoll">
		<jsp:include page="/WEB-INF/jsp/home/pageRoll.jsp" flush="true">
			<jsp:param value="forumAction_show?id=${forum.id}" name="action"/>
		</jsp:include>
</div>

<!-- ------------------------------------------------------------------------------------ -->
<!--发表新帖-->
<div id="addUI">
		<!-- 表单区域 -->
		<div id="addForm">
			<form id="form1"  action="topicAction_add.action" method="post">
				<input type="hidden" name="forumId" value="${forum.id}" />
				<p id="forum_title">标题:<input id="title" type="text" name="title" />&nbsp;<font size="4px;">最多只能输入500个字</font></p>
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
				var title=document.getElementById("title");
					
				var button=document.getElementById("button");
				button.onclick=function(){
					var username = '${sessionScope.user.username}';
					if (username== "") {
						alert("请登录后再操作!");
						return false;
					}
					if(title.value.length<1){
						alert("标题不能为空");
						return false;
					}
					if(title.value.length>500){
						alert("您输入字数超出规定");
						return false;
					}else{
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
