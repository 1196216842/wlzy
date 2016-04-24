<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>修改密码</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/user_addUI.css" />
</head>

<body>
<div id="list">
		<div class="top"><jsp:include page="/top.jsp"></jsp:include></div>
		<div class="nav">
			<a href="#">当前位置：</a>
			<a href="homeAction_list.action">首页<img
				src="${pageContext.request.contextPath }/images/arrow_close.gif" /></a> 
			<a href="userAction_list.action">用户管理<img
				src="${pageContext.request.contextPath }/images/arrow_close.gif" /></a>
			<a href="userAction_passwordUI.action">修改密码</a>
		</div>
		<div class="content">
			<div id="addUser">
					<p id="text">修改密码</p>
					<div id="style">
						<form action="userAction_changePassword.action" method="post">
						<input type="hidden" name="id" value="${sessionScope.user.id}" />
						<div>账号:${sessionScope.user.username}</div>
						<div style="color:red;">${requestScope.message}</div>
						<%-- <div>密码&nbsp;<input type="password" name="password" class="txt" value="${sessionScope.user.password}"/></div> --%>
						<div>请输入原密码:&nbsp;<input id="password1" type="password" name="passwords" class="txt"/></div>
						<div>请输入新密码:&nbsp;<input id="password2" type="password" name="passwords" class="txt"/></div>
						<div>再输入新密码:&nbsp;<input id="password3" type="password" name="passwords" class="txt"/></div>
						<div class="but" style="margin-left: 150px;"><input type="submit" type="button" value="修&nbsp;&nbsp改" onclick="return compare()"/></div>
						</form>
					</div>
			</div>
		</div>
</div>
<script type="text/javascript">
function compare(){
	var password1=document.getElementById("password1");
	var password2=document.getElementById("password2");
	var password3=document.getElementById("password3");
	if(password2.value==""||password3.value==""||password1==""){
		alert("密码不能为空");
		return false;
	}
	if(password2.value==password3.value){
		return true;
	}else{
		alert("两次输入的密码不正确");
		return false;
	}
}

</script>
</body>
</html>
