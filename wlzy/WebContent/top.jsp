<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>物理天地</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/myJS.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/layer/layer.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/top.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/login.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/list.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/font-awesome/css/font-awesome.min.css" />
<script type="text/javascript">
	$(function(){
	    $("#login1").click(function(){
	    	window.location.href="userAction_loginUI.action";
	    });
	    
	    $("#register1").click(function(){
	    	window.location.href="userAction_registerUI.action";
	    });
	});
function judge(){
	var user='${sessionScope.user}';
	if(user!=""){
		return true;
	}else{
		alert("请登录!");
		return false;
	}
}
</script>
</head>

<body>
<div id="index">
	<div class="first">
	  <s:if test="#session.user!=null">
		<div id="welcome">${sessionScope.user.username}<a href="userAction_cancel.action">&nbsp;&nbsp;注销</a></div> 
	  </s:if>
	  <s:else>
	 	<div id="login"><a id="register1" href="#">注册</a><a href="#" id="login1">登录</a></div>
	  </s:else>
	  <div id="list">
	    <s:if test="#session.user.userType==2">
			<a href="forumManageAction_list.action">板块管理</a>
			<a href="userAction_list.action">|&nbsp;用户管理&nbsp;|</a>
		</s:if>
		<a href="userAction_self.action" onclick="return judge()"><span class="fa fa-user"></span>&nbsp;个人中心</a>
		<a href="#" onclick="openwindow('homeAction_chat.action','${sessionScope.user.username}');"><span class="fa fa-comment"></span>&nbsp;聊天&nbsp;|</a>
		<a href="homeAction_list.action" ><span class="fa fa-home">&nbsp;</span>首页&nbsp;|</a><!--  -->
	   </div>
	</div>
	<div class="second">
		<img alt="" src="${pageContext.request.contextPath }/images/logo2.png">
	</div>
	
	<div class="third">
			<a href="homeAction_list.action"><span class="fa fa-home">&nbsp;</span>首页</a>
			<a href="forumAction_list.action">论坛天地</a>
			<a  href="myFileAction_noticeList.action?userId=0">教学中心</a>
			<a  href="examTopicAction_list.action" onclick="return judge()">在线测试</a>
			<a  href="myFileAction_expList.action" >物理实验</a>
			<a  href="myFileAction_authorList.action" >物理学家</a>
	</div>
	<div id="nav"></div>
</div>
</html>
