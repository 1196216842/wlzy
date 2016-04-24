<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>论坛天地</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/forum_list.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
$(function(){//后台传来个数由这个来判断有多少个版块，再计算高度
	var size='${requestScope.size}';
	var num=0;
	if((size%3)!=0){
		num=size/3+1;
	}else{
		num=size/3;
	}
	num=Math.floor(num);
	//共有num层，这num层的高度
	var top=num*114+12;
	$("#forum_list_forum").css("height",top);
});

</script>
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
		<a href="forumAction_list.action">论坛天地</a>
</div>
<div class="content">
	<div id="forum_list_hot">
		<div id="forum_list_hot_name">热门帖子</div>
		<div id="forum_list_hot_list">
			<s:iterator value="#topics">
				<div class="forum_hot_list">
					<p class="forum_hot_list_p1">
						<span>
							<s:a action="forumAction_show?id=%{forum.id}">[${forum.name}]
							</s:a>
						</span>
						
						<span class="forum_hot_list_title">
								<img src="${pageContext.request.contextPath }/images/topicType_${type}.gif" />
							<s:a action="topicAction_show?id=%{id}" title="%{title}">
									${title}
							</s:a>
						</span>
					</p>
					
					<p class="forum_hot_list_p2">
						<span><fmt:formatDate value="${postTime}" pattern="yyyy-MM-dd"/></span>
					</p>
				</div>
			</s:iterator>
		</div>
	
	</div>
	<div id="forum_list_forum">
		<s:iterator value="#forums" status="status" >
			<div class="forum_list_forum">
				<p class="name"><s:a action="forumAction_show?id=%{id}">${name}</s:a></p>
				<p>主题数：${topicCount}&nbsp;&nbsp;文章数：${articleCount}</p>
				<p>
					<span class="span1">
							最新发表：<s:a action="topicAction_show?id=%{lastTopic.id}" title="%{lastTopic.title}">${lastTopic.title}</s:a>
					</span>
					<span class="span2">
							<fmt:formatDate value="${lastTopic.postTime}" pattern="yyyy-MM-dd" />
					</span>
				</p>
			</div>
		</s:iterator>
	</div>
</div>


<div class="bottom">
<jsp:include page="/bottom2.jsp"></jsp:include>
</div>
</body>
</html>
