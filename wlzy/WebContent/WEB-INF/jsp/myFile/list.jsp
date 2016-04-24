<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<title>教学中心</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath }/css/myFile_list.css" />
<script type="text/javascript">

	function judge() {
		var username = '${sessionScope.user.username}';
		if (username == "") {
			alert("请登陆");
			return false;
		} else {
			return true;
		}
	}
	/***************上传视频******************/

	function videoShow() {
		$("#uploadVideo").show();
	}
	function videoHide() {
		$("#uploadVideo").hide();
	}

	function uploadVIDEO() {
		var value = $("#videoFile").val();
		var type = value.substring(value.lastIndexOf(".") + 1).toLowerCase();
		var flag = (type == "asx" || type == "asf" || type == "mpg"
				|| type == "wmv" || type == "3gp" || type == "mp4"
				|| type == "mov" || type == "avi" || type == "flv");
		if (flag) {
			//可以上传
			$("#videoForm").submit();
		} else {
			alert("对不起，类型为" + type
					+ "的文件不符合规范，请上传asx、asf、mpg、wmv、3gp、mp4、mov、avi、flv等类型的视频文件");
		}
	}
	/***************上传文档******************/
	function docShow() {
		$("#uploadDoc").show();
	}
	function docHide() {
		$("#uploadDoc").hide();
	}
	function uploadDOC() {
		var value=$("#docFile").val();
		var type=value.substring(value.lastIndexOf(".")+1).toLowerCase();
		var flag=(type=="pdf");
		if(flag){
			//可以上传
			$("#docForm").submit();
		}else{
			alert("对不起，类型为"+type+"的文件不符合规范，请上传pdf类型的文档文件");
		}
	}
	/***************上传资料******************/
	function resourceShow() {
		$("#uploadResource").show();
	}
	function resourceHide() {
		$("#uploadResource").hide();
	}
	function uploadRESOURCE() {
		var value=$("#resourceFile").val();
		var type=value.substring(value.lastIndexOf(".")+1).toLowerCase();
		if(type.length>0){
			//可以上传
			$("#resourceForm").submit();
		}else{
			alert("对不起，文件不能为空");
		}
	}
</script>
<script type="text/javascript">
	$(function(){
		/**
		*选择不同的老师，然后提交
		*根据userId也就是老师的ID来查找
		*/
		$("#select1").change(function(){
			var userId=$("#select1").val();
			window.location="myFileAction_noticeList.action?userId="+userId;
		});
		
		//根据dataType来变换颜色
		var dataType='${requestScope.dataType}';
		if(dataType==0){
			$("#notice").css("border","2px outset #fff");
			$("#notice").css("font-size","18px");
			$("#notice").css("background-color","green");
		}
		if(dataType==1){
			$("#video").css("border","2px outset #fff");
			$("#video").css("font-size","18px");
			$("#video").css("background-color","green");
		}
		if(dataType==2){
			$("#doc").css("border","2px outset #fff");
			$("#doc").css("font-size","18px");
			$("#doc").css("background-color","green");
		}
		/* if(dataType==3){
			$("#audio").css("border","2px outset #fff");
			$("#audio").css("font-size","18px");
			$("#audio").css("background-color","green");
		} */
		if(dataType==5){
			$("#resource").css("border","2px outset #fff");
			$("#resource").css("font-size","18px");
			$("#resource").css("background-color","green");
		}
		/**事件**/
		$("#notice").click(function(){
			window.location.href="myFileAction_noticeList.action?userId=${requestScope.userId}";
		});
		$("#video").click(function(){
			window.location.href="myFileAction_videoList.action?userId=${requestScope.userId}";
		});
		$("#doc").click(function(){
			window.location.href="myFileAction_docList.action?userId=${requestScope.userId}";
		});
		/* $("#audio").click(function(){
			window.location.href="myFileAction_audioList.action?userId=${requestScope.userId}";
		}); */
		$("#resource").click(function(){
			window.location.href="myFileAction_otherList.action?userId=${requestScope.userId}";
		});
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
	</a> <a href="myFileAction_list.action?userId=0&fileType=4">教学中心
	</a>
</div>
<div class="content">
	<div id="left">
		<div id="choiceTeacher">
			<p id="p1">选择老师</p>
			<p id="p2">
				<select id="select1">
					 <s:if test="#userId==0">
						<option selected="selected" value="0">全部</option>
					  </s:if>
					  <s:else>
						<option value="0">全部</option>
				      </s:else>
				     <s:iterator value="#teachers">
					  <s:if test="#userId==id">
						<option selected="selected"  value="${id}">
						${name}
						</option>
					  </s:if>
					  <s:else>
						<option value="${id}">
							${name}
						</option>
					 </s:else>
				     </s:iterator>
			     </select>
			</p>
		</div>
		
		<div id="choicePage">
			<div id="notice">教务通知</div>
			<div id="video">在线视频</div>
			<div id="doc">精品阅读</div>
			<!-- <div id="audio">在线音频</div> -->
			<div id="resource">资料下载</div>
		</div>
	</div>
	<div id="right">
	
		<s:if test="#dataType==0">
			<div id="notice_page">
				<div id="notice_page_name">
					教务通知
					<s:if test="#session.user.userType==1 or #session.user.userType==2">
						<a href="noticeAction_addUI.action?userId=${requestScope.userId }">[发布]</a>
					</s:if>
				</div>
				<div id="notice_page_list">
					<s:iterator value="#notices">
						<div class="notices">
							<span class="notice_name"><a href="userAction_other.action?id=${user.id}">${user.name}</a></span>
							<span class="notice_type">
									<s:if test="type==0">
										<span style="color:blue;">【普通】</span>
									</s:if>
									<s:if test="type==1">
										<span style="color:orange;">【重要】</span>
									</s:if>
									<s:if test="type==2">
										<span style="color:red;">【紧急】</span>
									</s:if>
							</span>
							
							<a  title="${title}"  class="notice_title" href="noticeAction_show.action?id=${id}">${title}</a>
							<span class="notice_postTime"><fmt:formatDate value="${postTime}" pattern="yyyy-MM-dd" /></span>
									<span class="notice_tools">
									<a href="noticeAction_show.action?id=${id}">查看</a>
								<s:if test="#session.user.userType==2">
										<a href="noticeAction_editUI.action?id=${id}">修改</a>
										<a  href="noticeAction_delete.action?id=${id}&userId=${requestScope.userId}" onClick="return confirm('确定要删除这条通知？')">删除</a>
								</s:if>
									</span>
						</div>
					</s:iterator>
				</div>
			</div>
		</s:if>
		
		<s:if test="#dataType==1">
			<div id="video_page">
				<div id="video_page_name">
					在线视频
					<s:if test="#session.user.userType==1 or #session.user.userType==2">
						<a href="#"  onclick="videoShow()">[上传]</a>
					</s:if>
				</div>
				<div id="video_page_list">
					<s:iterator value="#videos">
 						<div class="videos">
 						<span class="video_name"><a href="userAction_other.action?id=${user.id}">${user.name}</a></span>
						<a  title="${trueName}"  class="video_trueName" href="fileHandleAction_play.action?id=${id}">${trueName}</a>
							<span class="vedio_uploadTime"><fmt:formatDate value="${uploadTime}" pattern="yyyy-MM-dd" /></span>
									<span class="video_tools">
										<a class="fa fa-youtube-play" href="fileHandleAction_play.action?id=${id}">播放</a>
										<a href="fileHandleAction.action?uuidName=${uuidName}&fileType=${fileType}&trueName=${trueName}" onclick="return judge();">下载</a>
										<s:if test="#session.user.userType==2">
											<a href="myFileAction_delete.action?id=${id}&userId=${requestScope.userId}" onClick="return confirm('确定要删除这条通知？')">删除</a>
										</s:if>
									</span>
 						</div>
					</s:iterator>
				</div>
			</div>
		</s:if>
		
		<s:if test="#dataType==2">
			<div id="doc_page">
				<div id="doc_page_name">
					精品阅读
					<s:if test="#session.user.userType==1 or #session.user.userType==2">
						<a href="#" onclick="docShow()">[上传]</a>
					</s:if>
				</div>
				<div id="doc_page_list">
					<s:iterator value="#docs">
 						<div class="docs">
 						<span class="doc_name"><a href="userAction_other.action?id=${user.id}">${user.name}</a></span>
						<a  title="${trueName}"  class="doc_trueName" href="fileHandleAction_readDoc.action?id=${id}">${trueName}</a>
							<span class="doc_uploadTime"><fmt:formatDate value="${uploadTime}" pattern="yyyy-MM-dd" /></span>
									<span class="doc_tools">
										<a href="fileHandleAction_readDoc.action?id=${id}">查看</a>
										<a href="fileHandleAction.action?uuidName=${uuidName}&fileType=${fileType}&trueName=${trueName}" onclick="return judge();">下载</a>
										<s:if test="#session.user.userType==2">
											<a href="myFileAction_delete.action?id=${id}&userId=${requestScope.userId}" onClick="return confirm('确定要删除这条通知？')">删除</a>
										</s:if>
									</span>
 						
 						</div>
					</s:iterator>
				</div>
			</div>
		</s:if>
		
		<%-- <s:if test="#dataType==3">
			<div id="audio_page">在线音频</div>
		</s:if> --%>
		<!-- ------图片功能不要了------ -->
		<s:if test="#dataType==5">
			<div id="resource_page">
			<div id="resource_page_name">
					资料下载
					<s:if test="#session.user.userType==1 or #session.user.userType==2">
						<a href="#" onclick="resourceShow()">[上传]</a>
					</s:if>
				</div>
				<div id="resource_page_list">
					<s:iterator value="#others">
 						<div class="resources">
 						<span class="resource_name"><a href="userAction_other.action?id=${user.id}">${user.name}</a></span>
						<span  title="${trueName}"  class="resource_trueName" >${trueName}</span>
							<span class="resource_uploadTime"><fmt:formatDate value="${uploadTime}" pattern="yyyy-MM-dd" /></span>
									<span class="resource_tools">
										<a href="fileHandleAction.action?uuidName=${uuidName}&fileType=${fileType}&trueName=${trueName}" onclick="return judge();">下载</a>
										<s:if test="#session.user.userType==2">
											<a  href="myFileAction_delete.action?id=${id}&userId=${requestScope.userId}" onClick="return confirm('确定要删除这条通知？')">删除</a>
										</s:if>
									</span>
 						
 						</div>
					</s:iterator>
				</div>
			</div>
		</s:if>	
	</div>
</div>

<!-- 上传的弹窗-->
	<div id="uploadVideo">
		<p>视频上传</p>
		<p title="asx、asf、mpg、wmv、3gp、mp4、mov、avi、flv">
			支持格式:asx、asf、mpg、wmv、3gp、mp4、mov、avi、flv
		</p>
		<form id="videoForm" action="fileHandleAction_uploadVIDEO.action"
				method="post" enctype="multipart/form-data">
			<input type="hidden" name="userId" value="${userId}">
			<input id="videoFile" class="file" type="file" name="file"><br/>
			<input class="submit" type="button" value="上传"
					onclick="uploadVIDEO()">
			<input class="submit" type="button" value="关闭"
					onclick="videoHide()">
		</form>
	</div>
	<div id="uploadDoc">
		<p>文档上传</p>
		<p title="pdf">
			支持格式:pdf
		</p>
		<form id="docForm" action="fileHandleAction_uploadDOC.action"
				method="post" enctype="multipart/form-data">
			<input type="hidden" name="userId" value="${userId}">
			<input id="docFile" class="file" type="file" name="file"><br/>
			<input class="submit" type="button" value="上传"
					onclick="uploadDOC()">
			<input class="submit" type="button" value="关闭"
					onclick="docHide()">
		</form>
	</div>
 <div id="uploadResource">
 	<p>上传资料</p>
		<p title="任何格式(视频，文档，压缩包。。。)，只支持下载">
			支持格式:任何格式(视频，文档，压缩包。。。)，只支持下载
		</p>
		<form id="resourceForm" action="fileHandleAction_uploadOTHER.action"
				method="post" enctype="multipart/form-data">
			<input type="hidden" name="userId" value="${userId}">
			<input id="resourceFile" class="file" type="file" name="file"><br/>
			<input class="submit" type="button" value="上传"
					onclick="uploadRESOURCE()">
			<input class="submit" type="button" value="关闭"
					onclick="resourceHide()">
		</form>
 </div>
 <!-- ------弹出框结束---------- -->
<div class="pageRoll">
		<jsp:include page="/WEB-INF/jsp/home/pageRoll.jsp" flush="true"></jsp:include>
		<%-- <jsp:param value="userAction_list" name="action"/> 不需要为空的话默认地址栏--%>
</div>
<div class="bottom">
<jsp:include page="/bottom2.jsp"></jsp:include>
</div>
</body>
</html>
