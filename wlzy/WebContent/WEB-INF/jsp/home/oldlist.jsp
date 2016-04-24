<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>首页</title>

</head>

<body>
<div id="list">
	<div class="top"><jsp:include page="/top.jsp"></jsp:include></div>
	<div class="nav">
		<a href="#">当前位置：</a>
		<a href="homeAction_list.action">首页</a>
	</div>
	<div class="content">
		<div id="left">
			<div id="notice">
				<p id="noticeName">
					<span style="float:left;">教务通知</span>
					<span style="float:right;padding-right:10px;">
					<s:a action="myFileAction_noticeList?userId=0">更多>></s:a>
					</span>
				</p>
				<div id="noticeList">
					<s:iterator value="notices">
						 <div class="noticeList">
								<span class="noticeName"><a href="userAction_other.action?id=${user.id}">${user.name}</a></span>
								<a href="noticeAction_show.action?id=${id}">
									<span class="noticeTitle"  title="${title}">${title}</span>
									<%-- <span>${content}</span> --%>
									<span class="noticePostTime">
										<fmt:formatDate value="${postTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
									</span>
								</a> 
						</div>
					</s:iterator>
				</div>
			</div>
			
			<div id="topic">
				<p id="topicName">
				<span style="float:left;">最新帖子</span>
				<span style="float:right;padding-right:10px;">
				<s:a action="forumAction_list">更多>></s:a>
				</span>
				</p>
				<div id="topicList">
				<s:iterator value="topics">
						 <div class="topicList">
								<span class="topicName"><a href="userAction_other.action?id=${user.id}">${user.name}</a></span>
								<span class="topicForum"><a href="forumAction_show.action?id=${forum.id }">[${forum.name}]</a></span>
								<a href="topicAction_show.action?id=${id}">
									<span class="topicTitle"  title="${title}">${title}</span>
									<%-- <span>${content}</span> --%>
									<span class="topicPostTime">
										<fmt:formatDate value="${postTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
									</span>
								</a> 
						</div>
					</s:iterator>
				</div>
			</div>
			
			<div id="author">
				<p id="authorName">
				<span style="float:left;">物理学家介绍</span>
				<span style="float:right;padding-right:10px;">
				<s:a action="myFileAction_authorList">更多>></s:a>
				</span>
				</p>
				<div id="authorList">
				<s:iterator value="authors">
						 <div class="authorList">
								<span class="authorName"><a href="userAction_other.action?id=${user.id}">${user.name}</a></span>
								<a href="fileHandleAction_readDoc.action?id=${id}">
									<span class="authorTitle"  title="${trueName}">${trueName}</span>
									<span class="authorUploadTime">
										<fmt:formatDate value="${uploadTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
									</span>
								</a> 
						</div>
					</s:iterator>
				</div>
			</div>
			
			<div id="exp">
				<p id="expName">
				<span style="float:left;">物理实验介绍</span>
				<span style="float:right;padding-right:10px;">
				<s:a action="myFileAction_expList">更多>></s:a>
				</span>
				</p>
				<div id="expList">
				<s:iterator value="exps">
						 <div class="expList">
								<span class="expName"><a href="userAction_other.action?id=${user.id}">${user.name}</a></span>
								<a href="fileHandleAction_readDoc.action?id=${id}">
									<span class="expTitle"  title="${trueName}">${trueName}</span>
									<span class="expUploadTime">
										<fmt:formatDate value="${uploadTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
									</span>
								</a> 
						</div>
					</s:iterator></div>
			</div>
		</div>
		
		<div id="right">
			<div id="quick">
				<p id="p1">快速导航</p>
				<s:a action="myFileAction_noticeList?userId=0"><p>教务公告</p></s:a>
				<s:a action="myFileAction_videoList?userId=0"><p>教学视频</p></s:a>
				<s:a action="myFileAction_docList?userId=0"><p>课件文档</p></s:a>
				<s:a action="myFileAction_audioList?userId=0"><p>音频分享</p></s:a>
				<s:a action="myFileAction_imageList?userId=0"><p>图片浏览</p></s:a>
				<s:a action="myFileAction_otherList?userId=0"><p>其他资料</p></s:a>
				<p id="p2"></p>
				<s:iterator value="forums">
					<s:a action="forumAction_show?id=%{id}"><p>${name }</p></s:a>
				</s:iterator>
				
			</div>
			<%-- <div id="calendar">这里放日历<span onclick="alert('ddddd');">AAAAA</span></div>
			<div id="material">这里放资源下载</div> --%>
		</div>
	</div>
</div>
<div 
style="width:79%;
		height:1px;
		background: #ddd;
		position: absolute;
		top:1250px;
		left:140px;
		margin: 0px auto;">
</div>
</body>
</html>
