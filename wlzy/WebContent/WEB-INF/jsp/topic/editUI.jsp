<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>修改主题</title>
<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath }/css/topic_edit.css" />

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
			</a>
			<%-- <a href="topicAction_show.action?id=${topic.id }">${requestScope.topic.title}
			<img
				src="${pageContext.request.contextPath }/images/arrow_close.gif" />
			</a>
 --%>			<a href="#">修改主题</a>
</div>
<div class="content">
<!--发表新帖-->
<div id="addUI">
		<!-- 表单区域 -->
		<div id="addForm">
			<div id="addForum_content" style="display:none;">${requestScope.topic.content}</div>
			<form id="form1"  action="topicAction_edit.action" method="post">
				<input type="hidden" name="id" value="${requestScope.topic.id}" />
				<p>修改:<input id="title" type="text" name="title" value="${requestScope.topic.title }"/><font size="4px;">最多只能输入500个字</font></p>
				<script name="content"    id="editor" type="text/plain" style="width:100%;height:300px;"></script>
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
					}if(title.value.length<1){
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
			 function getContent(){
					var oldContent=document.getElementById("addForum_content");
					UE.getEditor('editor').setContent(oldContent.innerHTML); 
				} 
			</script>
</div> 

</div>
<div class="bottom">
<jsp:include page="/bottom2.jsp"></jsp:include>
</div>
</body>
</html>
