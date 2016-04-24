<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE>
<html>
<head>
<title>${requestScope.vedio.title}</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/jwplayer-7.1.4/jwplayer.js"></script>
<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath }/css/myFile_play.css" />
</head>

<body>
	<div id="list">
			<div class="top"><jsp:include page="/top.jsp"></jsp:include></div>
			<div class="nav">
				<a href="#">当前位置：</a>
				<a href="homeAction_list.action">首页<img
					src="${pageContext.request.contextPath }/images/arrow_close.gif" />
				</a> 
				 <a href="myFileAction_noticeList.action?userId=0">教学中心<img
				src="${pageContext.request.contextPath }/images/arrow_close.gif" />
			</a> 
			<a>播放</a>
			</div>
			<div class="content">
				<div id="leftVideo">
					<div id="title">${requestScope.video.title}</div>
					<div id="play">
						<p>名称:${requestScope.video.trueName}</p>
						<p>作者:<a href="userAction_other.action?id=${requestScope.video.user.id}">${requestScope.video.user.name}</a></p>
						<p>
							时间:${requestScope.video.uploadTime}
							<a href="fileHandleAction.action?uuidName=${requestScope.video.uuidName}&fileType=${requestScope.video.fileType}&trueName=${requestScope.video.trueName}" onclick="return judge();">下载</a>
							<a href="#" onclick="javascript:history.go(0)">刷新</a>
						</p>
			 			<div id="a1"></div> 
			 			<div id="look">温馨提示：若视频加载失败，可能是视频在缓冲中,请等耐心等一会儿再刷新</div>
						<script type="text/javascript" src="${pageContext.request.contextPath }/ckplayer/ckplayer.js"
							charset="utf-8"></script>
						<script type="text/javascript">
							var flashvars = {
								  f : '${pageContext.request.contextPath }/${requestScope.truePath}',
								  c : 0
							};
							var params = {
								bgcolor : '#FFF',
								allowFullScreen : true,
								allowScriptAccess : 'always',
								wmode : 'transparent'
							};
							CKobject.embedSWF('${pageContext.request.contextPath }/ckplayer/ckplayer.swf', 'a1',
									'ckplayer_a1', '550', '350', flashvars, params);
								
						</script>  
						</div>
					</div>
					<div id="rightVideo">
					<div id="videoTitle">最新视频 </div>
						<s:iterator value="#videos">
							<div class="rightVideo">
								<p class="vedioName">作者:&nbsp;<a href="userAction_other.action?id=${user.id}">${user.name}</a></p>
								<p class="vedioTitle" title="${title}">标题:&nbsp;${title}</p>
								<p class="vedioTrueName" title="${trueName}">文件名:&nbsp;${trueName}</p>
								<p class="vedioPostTime">日期:&nbsp;<fmt:formatDate value="${uploadTime}" pattern="yyyy-MM-dd HH:mm:ss" /></p> 
								<p class="vedioTools">
									<a href="fileHandleAction_play.action?id=${id}">播放</a>
									<a href="fileHandleAction.action?uuidName=${uuidName}&fileType=${fileType}&trueName=${trueName}" onclick="return judge();">下载</a>
									<s:if test="#session.user.userType==2">
										<a href="#" onClick="return confirm('确定要删除这条通知？')">删除</a>
									</s:if>
								</p>
							</div>
						</s:iterator>
					</div>
					
					</div> 
				</div>
	</div>
</body>
	<script type="text/javascript">
		function judge(){
			var username='${sessionScope.user.username}';
			if(username==""){
				alert("登陆后才能下载");
				return false;
			}else{
				return true;
			}
		}
	
	</script>
</html>
