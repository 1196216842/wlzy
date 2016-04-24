<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>物理学家介绍</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/author.css" />
</head>
<body>
	<div id="list">
		<div class="top"><jsp:include page="/top.jsp"></jsp:include></div>
		<div class="nav">
			<a href="#">当前位置：</a>
			<a href="homeAction_list.action">首页<img
				src="${pageContext.request.contextPath }/images/arrow_close.gif" />
			</a> <a href="myFileAction_authorList.action">物理学家</a>
		</div>
		<div class="content">
			<div id="author">
				<div id="authorTitle">物理学家</div>
					<s:iterator value="#authors">
					<div class="authors">
						<p class="name">作者:&nbsp;<a href="userAction_other.action?id=${user.id}">${user.name}</a></p>
						<p class="title" title="${title}">标题:&nbsp;${title}</p>
						<p class="trueName" title="${trueName}">文件名:&nbsp;${trueName}</p>
						<p class="postTime">日期:&nbsp;<fmt:formatDate value="${uploadTime}" pattern="yyyy-MM-dd HH:mm:ss" /></p> 
						<p class="tools">
						    <a href="fileHandleAction_readDoc.action?id=${id}">查看</a>
							<a href="fileHandleAction.action?uuidName=${uuidName}&fileType=${fileType}&trueName=${trueName}" onclick="return judge();">下载</a>
							<s:if test="#session.user.userType==2">
									<a href="myFileAction_delete.action?id=${id}" onClick="return confirm('确定要删除这条通知？')">删除</a>
							</s:if>
						</p>
					</div>
					</s:iterator>
				</div>
			</div>
			<div class="pageRollFile">
			<jsp:include page="/WEB-INF/jsp/home/pageRoll.jsp" flush="true"></jsp:include>
			<%-- <jsp:param value="userAction_list" name="action"/> 不需要为空的话默认地址栏--%>
			</div>
			<div id="tools">
			<s:if test="#session.user.userType==2||#session.user.userType==1">
			<form id="authorForm" action="fileHandleAction_uploadAUTHOR.action" method="post" enctype="multipart/form-data">
					<p>标题: <input class="title" type="text" name="title"></p> 
					<p> <input id="authorFile" class="file" type="file" name="file"></p>
					<p class="uploadType" title="word、pdf">支持:&nbsp;word、pdf</p>
					<p> <input class="submit" type="button" value="上传" onclick="uploadAUTHOR()"></p>
			</form>
			</s:if>
			</div>
		</div>
</body>
<script type="text/javascript">
function judge(){
			var username='${sessionScope.user.username}';
			if(username==""){
				alert("请登陆");
				return false;
			}else{
				return true;
			}
		}
function uploadAUTHOR(){
		var value=$("#authorFile").val();
		var type=value.substring(value.lastIndexOf(".")+1).toLowerCase();
		var flag=(type=="pdf"||type=="doc"||type=="docx");
		if(flag){
			//可以上传
			$("#authorForm").submit();
		}else{
			alert("对不起，类型为"+type+"的文件不符合规范，请上传word、pdf等类型的文档文件");
		}
	}

</script>
</html>
