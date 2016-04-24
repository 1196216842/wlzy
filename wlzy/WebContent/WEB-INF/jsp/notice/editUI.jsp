<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>修改通知</title>
<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath }/css/notice_UI.css" />
</head>
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
<a href="#">修改通知</a>
</div>
<div class="content">
<!--发表新帖-->
<div id="addUI">
		<!-- 表单区域 -->
		<div id="addForm">
		<div id="addForum_content" style="display:none;">${requestScope.notice.content}</div>
			<form id="form1"  action="noticeAction_edit.action" method="post">
			<input type="hidden" name="id" value="${requestScope.notice.id}">
				<p id="notice_title">标题:<input id="title" type="text" name="title" value="${requestScope.notice.title }"/><font size="4px;">&nbsp;最多只能输入500个字</font></p>
				<p id="notice_type">
							通知类型:
							<select name="type">
								<s:if test="#notice.type==0">
									<option value="0" selected="selected">普通</option>
								</s:if>
								<s:else>
									<option value="0">普通</option>
								</s:else>
								
								<s:if test="#notice.type==1">
									<option value="1" selected="selected">重要</option>
								</s:if>
								<s:else>
									<option value="1">普通</option>
								</s:else>
								<s:if test="#notice.type==2">
									<option value="2" selected="selected">紧急</option>
								</s:if>
								<s:else>
									<option value="2">紧急</option>
								</s:else>
							</select>
				</p>
				<script name="content"   id="editor" type="text/plain" style="width:100%;height:300px;"></script>
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

