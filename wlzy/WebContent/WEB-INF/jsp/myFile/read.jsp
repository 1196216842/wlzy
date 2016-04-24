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
<title>${requestScope.doc.title}</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/jwplayer-7.1.4/jwplayer.js"></script>
<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath }/css/myFile_read.css" />
</head>

<body>
	<div id="list">
			<div class="top"><jsp:include page="/top.jsp"></jsp:include></div>
			<div class="nav">
				<a href="#">当前位置：</a>
				<a href="homeAction_list.action">首页<img
					src="${pageContext.request.contextPath }/images/arrow_close.gif" />
				</a> 
				<s:if test="#doc.fileType==6">
				 	<a href="myFileAction_authorList.action">物理学家<img
						src="${pageContext.request.contextPath }/images/arrow_close.gif" />
					</a>
				</s:if>
				<s:if test="#doc.fileType==7">
				 	<a href="myFileAction_expList.action">物理实验<img
						src="${pageContext.request.contextPath }/images/arrow_close.gif" />
					</a>
				</s:if>
				<s:if test="#doc.fileType==2">
				 	<a href="myFileAction_noticeList.action?userId=0">教学中心<img
					src="${pageContext.request.contextPath }/images/arrow_close.gif" />
				</a>
				</s:if>
				  
			<a>阅读</a>
			</div>
			<div class="content">
				<div id="read">
					<%-- <div id="title">${requestScope.doc.title}</div> --%>
					${requestScope.doc.trueName}
				</div>
				<div id="viewDoc">
					<span>作者:<a href="userAction_other.action?id=${requestScope.doc.user.id}">${requestScope.doc.user.name}</a></span>
					<span>
						<a href="fileHandleAction.action?uuidName=${requestScope.doc.uuidName}&fileType=${requestScope.doc.fileType}&trueName=${requestScope.doc.trueName}" onclick="return judge();">下载</a>
						<a href="#" onclick="javascript:history.go(0)">刷新</a>
					</span>	
					<span>时间:${requestScope.doc.uploadTime}</span>
						<%-- <embed width="100%" height="3000px"  src="${pageContext.request.contextPath}/${requestScope.docPath}"> --%>
						<%-- <iframe src="${pageContext.request.contextPath}/${requestScope.docPath}" width="800" height="600"></iframe> --%> 
					<object classid="clsid:CA8A9780-280D-11CF-A24D-444553540000" width="100%" height="100%" border="0"><!--IE--> 
					      <param name="_Version" value="65539"> 
					      <param name="_ExtentX" value="20108"> 
					      <param name="_ExtentY" value="10866"> 
					      <param name="_StockProps" value="0"> 
					      <param name="SRC" value="${pageContext.request.contextPath}/${requestScope.docPath}"> 
						<embed src="${pageContext.request.contextPath}/${requestScope.docPath}" width="100%" height="1400"></embed><!--FF--> 
					</object>
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
