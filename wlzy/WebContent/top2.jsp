<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>头部</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/font-awesome/css/font-awesome.min.css" />
<script type="text/javascript">
	$(function(){
	    $("#login").click(function(){
	    	window.location.href="userAction_loginUI.action";
	    });
	    
	    $("#register").click(function(){
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
<style type="text/css">
*{padding:0px;margin:0px;}
.nav{
	width:991px;height:30px;
	background: #ffffcc;
	margin:0px auto; 
	margin-top:10px;
	line-height: 30px;
	text-indent: 3px;
}
a{
	text-decoration: none;
}
 a:hover{
	color: orange;
}
#top_index{
	width:991px;
	height:170px;
	margin:0px auto;
}
#top_index #top_first{
	width:991px;
	height:30px;
	background: #3399aa;
	font-family:"微软雅黑";
}
#top_index #top_first a{
	font-size: 16px;
	text-decoration: none;
	color:white;
}
#top_index #top_first #top_welcome{
	float:left;
	line-height: 30px;
	text-indent: 3px;
}
#top_index #top_first #top_welcome a{
	float:left;
	line-height: 30px;
}
#top_index #top_first #top_choice{
	float:right;
	line-height: 30px;
	padding-right: 3px;
}
#top_index #top_first #top_choice a{
	float:left;
	line-height: 30px;
	margin-right: 3px;
}
#top_index #top_second{
	width:991px;
	height:80px;
}
#top_index #top_second img{
	width:991px;
	height:80px;
}
#top_index #top_third{
	width:991px;
	height:30px;
	font-family:"微软雅黑";
}
#top_index #top_third a{
	float:left;
	text-align:center;
	width:163.1px;
	line-height: 30px;
	text-decoration:none; 
	color: white;
	font-size:15px;
	background: #78b;
	border-left: 1px groove #267da8;
	border-right: 1px groove #267da8;
}
</style>
</head>

<body>
<div id="top_index">
	<div id="top_first">
		<div id="top_welcome">
			<s:if test="#session.user!=null">
				<a href="userAction_self.action">${sessionScope.user.username}</a>
				<a href="userAction_cancel.action">注销</a>
			</s:if>
			<s:else>
				<a href="#" id="login">登录</a>
				<a href="#" id="register">注册</a>
			</s:else>
		</div>
		
		<div id="top_choice">
			<a  href="homeAction_list.action"><span class="fa fa-home"></span>首页|</a>
			<a  href="#" onclick="openwindow('homeAction_chat.action','${sessionScope.user.username}');"><span class="fa fa-comment"></span>聊天|</a>
			<a  href="userAction_self.action" onclick="return judge()"><span class="fa fa-user"></span>个人中心</a>
			 <s:if test="#session.user.userType==2">
				<a href="userAction_list.action">|用户管理|</a>
				<a href="forumManageAction_list.action">版块管理</a>
			</s:if>
		</div>
	</div>
	
	<div id="top_second">
		<img alt="logo" src="${pageContext.request.contextPath }/images/logo2.png">
	</div>

	<div id="top_third">
		<a class="fa fa-home" href="homeAction_list.action">首页</a>
		<a href="forumAction_list.action">论坛天地</a>
		<a href="myFileAction_noticeList.action?userId=0">教学中心</a>
		<a href="examTopicAction_list.action" onclick="return judge()">在线测试</a>
		<a href="myFileAction_expList.action" >物理实验</a>
		<a href="myFileAction_authorList.action" >物理学家</a>
	</div>
</div>
</html>
