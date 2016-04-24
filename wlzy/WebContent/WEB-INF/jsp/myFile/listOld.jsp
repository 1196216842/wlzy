<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>论坛天地</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath }/js/myFileJs.js"></script> --%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/myFile_list.css" />

<script type="text/javascript">
	$(function() {
		var teacherFile0 = $(".teacherFile:eq(0)");
		var teacherFile1 = $(".teacherFile:eq(1)");
		var teacherFile2 = $(".teacherFile:eq(2)");
		var teacherFile3 = $(".teacherFile:eq(3)");
		var teacherFile4 = $(".teacherFile:eq(4)");
		var teacherFile5 = $(".teacherFile:eq(5)");
		
		//根据dataType来变换颜色
		var dataType='${requestScope.dataType}';
		if(dataType==0){
			/* teacherFile0.css("background-color", "#ffffaa"); */
			teacherFile0.css("background-color", "#ffffff");
		}
		if(dataType==1){
			/* teacherFile1.css("background-color", "#eeeedd"); */
			teacherFile1.css("background-color", "#ffffff");
		}
		if(dataType==2){
			/* teacherFile2.css("background-color", "#dddddd"); */
			teacherFile2.css("background-color", "#ffffff");
		}
		if(dataType==3){
			/* teacherFile3.css("background-color", "#ccccdd"); */
			teacherFile3.css("background-color", "#ffffff");
		}
		if(dataType==4){
			teacherFile4.css("background-color", "#ffffff");
			/* teacherFile4.css("border", "1px solid #ddd"); */
		}
		if(dataType==5){
			teacherFile5.css("background-color", "#ffffff");
			/* teacherFile4.css("border", "1px solid #ddd"); */
		}
		
		teacherFile0.click(function() {
			window.location.href="myFileAction_noticeList.action?userId=${requestScope.userId}";
		});
		teacherFile1.click(function() {
		window.location.href="myFileAction_videoList.action?userId=${requestScope.userId}";
		});
		teacherFile2.click(function() {
		window.location.href="myFileAction_docList.action?userId=${requestScope.userId}";
		});
		teacherFile3.click(function() {
		window.location.href="myFileAction_audioList.action?userId=${requestScope.userId}";
		});
		teacherFile4.click(function() {
		window.location.href="myFileAction_imageList.action?userId=${requestScope.userId}";
		});
		teacherFile5.click(function() {
		window.location.href="myFileAction_otherList.action?userId=${requestScope.userId}";
		});
		
		$("#select1").change(function(){
			var userId=$("#select1").val();
			window.location="myFileAction_noticeList.action?userId="+userId;
		});
		$("#bigIMG span").click(function(){
			$("#bigIMG").hide();
		});
	});
	/**
	*用户可以上传的文档文件件类型
	*pdf doc ppt xls docx xlsx
	*（word/pdf/excel）
	*/
	function uploadDOC(){
		var value=$("#docFile").val();
		var type=value.substring(value.lastIndexOf(".")+1).toLowerCase();
		var flag=(type=="pdf"||type=="doc"||type=="ppt"||type=="xls"||type=="docx"||type=="xlsx");
		if(flag){
			//可以上传
			$("#docForm").submit();
		}else{
			alert("对不起，类型为"+type+"的文件不符合规范，请上传word、pdf、excel等类型的文档文件");
		}
	}
	/**
	*用户可以上传的视频文件类型
	*支持持类型：asx asf mpg wmv 3gp mp4 mov avi flv
	*/
	function uploadVIDEO(){
		var value=$("#videoFile").val();
		var type=value.substring(value.lastIndexOf(".")+1).toLowerCase();
		var flag=(type=="asx"||type=="asf"||type=="mpg"||type=="wmv"||type=="3gp"||type=="mp4"||type=="mov"||type=="avi"||type=="flv");
		if(flag){
			//可以上传
			$("#videoForm").submit();
		}else{
			alert("对不起，类型为"+type+"的文件不符合规范，请上传asx、asf、mpg、wmv、3gp、mp4、mov、avi、flv等类型的视频文件");
		}
	}
	/**
	*用户可以上传的音频文件类型
	*支持持类型：mp3
	*/
	function uploadAUDIO(){
		var value=$("#audioFile").val();
		var type=value.substring(value.lastIndexOf(".")+1).toLowerCase();
		var flag=(type=="mp3");
		if(flag){
			//可以上传
			$("#audioForm").submit();
		}else{
			alert("对不起，类型为"+type+"的文件不符合规范，请上传mp3格式的音频文件");
		}
	}
	/**
	*用户可以上传的图片文件类型
	*支持持类型：BMP、JPG、JPEG、PNG、GIF
	*/
	function uploadIMG(){
		var value=$("#imgFile").val();
		var type=value.substring(value.lastIndexOf(".")+1).toLowerCase();
		var flag=(type=="bpm"||type=="jpg"||type=="jpeg"||type=="png"||type=="gif");
		if(flag){
			//可以上传
			$("#imgForm").submit();
		}else{
			alert("对不起，类型为"+type+"的文件不符合规范，请上传BMP、JPG、JPEG、PNG、GIF格式的图形文件");
		}
	}
	/**
	*文件类型不限
	*/
	function uploadOTHER(){
		var value=$("#otherFile").val();
		var type=value.substring(value.lastIndexOf(".")+1).toLowerCase();
		if(type.length>0){
			//可以上传
			$("#otherForm").submit();
		}else{
			alert("对不起，文件不能为空");
		}
	}
	function judge(){
		var username='${sessionScope.user.username}';
		if(username==""){
			alert("请登陆");
			return false;
		}else{
			return true;
		}
	}
	function play(obj){
		$("#play").show();
		var path='${pageContext.request.contextPath}/upload/audios/'+obj.title;
		$("#play audio").attr("src",path);
	}
function check(obj){
		$("#bigIMG").show();
		$("#bigIMG img").attr("src",obj.title);
	}
	
	
	function nowDate(){
			var time=(new Date());
			var hh=time.getHours();
			var mm=time.getMinutes();
			var ss=time.getSeconds();
		 	if(hh<10){
				hh="0"+hh;
			}
			if(mm<10){
				mm="0"+mm;
			}
			if(ss<10){
				ss="0"+ss;
			} 
			var nowTime=hh+":"+mm+":"+ss;
			
			$("#nowTime").html("");
			var span=$("<span>");
			span.append("当前时间:"+nowTime);
			$("#nowTime").append(span);
			//alert(nowTime);
		}
	setInterval(nowDate,1000);
</script>
</head>
<body>
	<div id="list">
		<div class="top"><jsp:include page="/top.jsp"></jsp:include></div>
		<div class="nav">
			<a href="#">当前位置：</a>
			<a href="homeAction_list.action">首页<img
				src="${pageContext.request.contextPath }/images/arrow_close.gif" />
			</a> <a href="myFileAction_list.action?userId=0&fileType=4">教学中心
			</a>
		</div>

		<div class="content">
			<!--左侧导航栏 -->
			<div id="divLeft">
				<div id="choicTeacher">
				&nbsp;&nbsp;&nbsp;选择老师：<select id="select1">
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
				</div>
				<div id="teacherFile1" class="teacherFile">教务公告</div>
				<div class="teacherFile">教学视频</div>
				<div class="teacherFile">课件文档</div>
				<div class="teacherFile">音频分享</div>
				<div class="teacherFile">图片浏览</div>
				<div class="teacherFile">其他资料</div>
			</div>
			<div id="divCenter">
				<s:if test="#dataType==0">
					<div class="divSome" id="notice">
						<div id="noticeTitle">教务公告</div>
						<s:iterator value="#notices">
							<div class="noticeView">
								<span class="type1">
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
								<span class="name1"><a href="userAction_other.action?id=${user.id}">${user.name}</a></span>
								<span class="title1" title="${title}"><a href="noticeAction_show.action?id=${id}">${title}</a></span>
							<%-- 	<span class="content1">${content}</span> --%>
								<s:if test="#session.user.userType==2">
									<span class="tools1">
										<a href="noticeAction_editUI.action?id=${id}">修改</a>
										|<a  href="noticeAction_delete.action?id=${id}&userId=${requestScope.userId}" onClick="return confirm('确定要删除这条通知？')">删除</a>
									</span>
								</s:if>
								<span class="postTime1"><fmt:formatDate value="${postTime}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
							</div>
						</s:iterator>
					</div>
				</s:if>
				
				<s:if test="#dataType==1">
					<div class="divSome" id="vedio">
					<div id="vedioTitle">教学视频 </div>
						<s:iterator value="#videos">
							<div class="vedio">
								<p class="name2">作者:&nbsp;<a href="userAction_other.action?id=${user.id}">${user.name}</a></p>
								<p class="title2" title="${title}">标题:&nbsp;${title}</p>
								<p class="trueName2" title="${trueName}">文件名:&nbsp;${trueName}</p>
								<p class="postTime2">日期:&nbsp;<fmt:formatDate value="${uploadTime}" pattern="yyyy-MM-dd HH:mm:ss" /></p> 
								<p class="tools2">
									<a href="fileHandleAction_play.action?id=${id}"><span class="fa fa-youtube-play"></span>播放</a>
									<a href="fileHandleAction.action?uuidName=${uuidName}&fileType=${fileType}&trueName=${trueName}" onclick="return judge();">下载</a>
									<s:if test="#session.user.userType==2">
										<a href="myFileAction_delete.action?id=${id}&userId=${requestScope.userId}" onClick="return confirm('确定要删除这条通知？')">删除</a>
									</s:if>
								</p>
							</div>
						</s:iterator>
					</div>
				</s:if>
				<s:if test="#dataType==2">
					<div class="divSome" id="doc">
						<div id="docTitle">课件文档</div>
							<s:iterator value="#docs">
							<div class="docs">
								<p class="name3">作者:&nbsp;<a href="userAction_other.action?id=${user.id}">${user.name}</a></p>
								<p class="title3" title="${title}">标题:&nbsp;${title}</p>
								<p class="trueName3" title="${trueName}">文件名:&nbsp;${trueName}</p>
								<p class="postTime3">日期:&nbsp;<fmt:formatDate value="${uploadTime}" pattern="yyyy-MM-dd HH:mm:ss" /></p> 
								<p class="tools3">
									<a href="fileHandleAction_readDoc.action?id=${id}">查看</a>
									<a href="fileHandleAction.action?uuidName=${uuidName}&fileType=${fileType}&trueName=${trueName}" onclick="return judge();">下载</a>
									<s:if test="#session.user.userType==2">
										<a href="myFileAction_delete.action?id=${id}&userId=${requestScope.userId}" onClick="return confirm('确定要删除这条通知？')">删除</a>
									</s:if>
								</p>
							</div>
						</s:iterator>
						</div>
				</s:if>
				<s:if test="#dataType==3">
					<div class="divSome" id="audio">
					<div id="audioTitle">音频分享</div>
						<s:iterator value="#audios">
							<div class="audios">
								
								<%-- <p class="title4" title="${title}">标题:&nbsp;${title}</p> --%>
								<span class="trueName4" title="${trueName}">&nbsp;${trueName}</span>
								<%-- <span class="postTime4">日期:&nbsp;<fmt:formatDate value="${uploadTime}" pattern="yyyy-MM-dd HH:mm:ss" /></span>  --%>
								<span class="uploadTime4">
								<a href="userAction_other.action?id=${user.id}">[${user.name}]</a>
								&nbsp;<span title="<fmt:formatDate value="${uploadTime}" pattern="yyyy-MM-dd HH:mm:ss" />"><fmt:formatDate value="${uploadTime}" pattern="yyyy-MM-dd" /></span></span>
								<span class="tools4">
									<a href="#" title="${uuidName}" onclick=play(this)><span class="fa fa-play"></span><!-- 播放 --></a>
									<a href="fileHandleAction.action?uuidName=${uuidName}&fileType=${fileType}&trueName=${trueName}" onclick="return judge();">下载</a>
									<s:if test="#session.user.userType==2">
										<a href="myFileAction_delete.action?id=${id}&userId=${requestScope.userId}" onClick="return confirm('确定要删除这条通知？')">删除</a>
									</s:if>
									
								</span>
							</div>
						</s:iterator>
							<div id="play">
								<audio id="music" controls="controls" src="" autoplay="autoplay">
								</audio>
							</div>
					</div>
				</s:if>
				<s:if test="#dataType==4">
					<div class="divSome" id="image"><div id="imageTitle">图片浏览</div>
							<s:iterator value="#images">
							<div class="images">
								<span class="name5">作者:&nbsp;<a href="userAction_other.action?id=${user.id}">${user.name}</a></span>
								<%-- <p class="title5" title="${title}">标题:&nbsp;${title}</p> --%>
								<span class="trueName5" title="${trueName}">文件名:&nbsp;${trueName}</span>
								<img src="${pageContext.request.contextPath}/upload/images/${uuidName}" alt="ddd"/>
								<p class="postTime5" title="<fmt:formatDate value="${uploadTime}" pattern="yyyy-MM-dd HH:mm:ss" />">日期:&nbsp;<fmt:formatDate value="${uploadTime}" pattern="yyyy-MM-dd HH:mm:ss" /></p> 
								<span class="tools5">
									<a href="#" title="${pageContext.request.contextPath}/upload/images/${uuidName}" onclick=check(this)>查看大图</a>
									<a href="fileHandleAction.action?uuidName=${uuidName}&fileType=${fileType}&trueName=${trueName}" onclick="return judge();">下载</a>
									<s:if test="#session.user.userType==2">
										<a href="myFileAction_delete.action?id=${id}&userId=${requestScope.userId}" onClick="return confirm('确定要删除这条通知？')">删除</a>
									</s:if>
								</span>
							</div>
						</s:iterator>
					</div>
					<!-- --------------------------图片显示----------------------- -->
					<div id="bigIMG">
						<span >关闭</span>
						<img alt="" src="">
					</div>
					<!-- --------------------------图片显示结束----------------------- -->
				</s:if>
				<!-- 其他资料 -->
				<s:if test="#dataType==5">
					<div class="divSome" id="other">
						<div id="otherTitle">资料下载</div>
						<s:iterator value="#others">
							<div class="otherView">
								<span class="name6"><a href="userAction_other.action?id=${user.id}">${user.name}</a></span>
								<span class="trueName6" title="${trueName}">&nbsp;${trueName}</span>
								<span class="tools6">
									<a href="fileHandleAction.action?uuidName=${uuidName}&fileType=${fileType}&trueName=${trueName}" onclick="return judge();">下载</a>
									<s:if test="#session.user.userType==2">
										|<a  href="myFileAction_delete.action?id=${id}&userId=${requestScope.userId}" onClick="return confirm('确定要删除这条通知？')">删除</a>
									</s:if>
								</span>
								<span class="postTime6"><fmt:formatDate value="${uploadTime}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
							</div>
						</s:iterator>
					</div>
				</s:if>
			</div>
			<div id="divRight">
				<div id="fileTool">
					<s:if test="#dataType==0">
						<div id="noticeTool">
							<p>模块信息:用来给老师发布一些教务公告，方便学生实时了解一些教学计划。</p>
							<s:if test="#session.user.userType==1 or #session.user.userType==2">
							<a href="noticeAction_addUI.action?userId=${requestScope.userId }">发布新公告</a>
							</s:if>
						</div>
					</s:if>
					
					<s:if test="#dataType==1">
						<div id="vedioTool">
							<p>模块信息:用来上传教学视频。</p>
							<s:if test="#session.user.userType==1 or #session.user.userType==2">
							<div class="fileForm">
								<form id="videoForm" action="fileHandleAction_uploadVIDEO.action" method="post" enctype="multipart/form-data">
								<input  type="hidden" name="userId" value="${userId}">
					 			<p>标题: <input class="title" type="text" name="title"></p> 
					    			<p> <input id="videoFile" class="file" type="file" name="file"></p>
					     		 	<p class="uploadType" title="asx、asf、mpg、wmv、3gp、mp4、mov、avi、flv">支持:&nbsp;asx、asf、mpg、wmv、3gp、mp4、mov、avi、flv</p>
					     		 	<p> <input class="submit" type="button" value="上传视频" onclick="uploadVIDEO()"></p>
								 </form>
				 			</div>
				 			</s:if>
						</div>
					</s:if>
					
					<s:if test="#dataType==2">
						<div id="docTool">
							<p>模块信息:用来上传教学文档。</p>
							<s:if test="#session.user.userType==1 or #session.user.userType==2">
							<div class="fileForm">
								<form id="docForm" action="fileHandleAction_uploadDOC.action" method="post" enctype="multipart/form-data">
								<input type="hidden" name="userId" value="${userId}">
					 			<p>标题: <input class="title" type="text" name="title"></p> 
					    			<p> <input id="docFile" class="file" type="file" name="file"></p>
					     		 	<p class="uploadType" title="word、pdf、excel,ppt">支持:&nbsp;word、pdf、excel,ppt</p>
					     		 	<p> <input class="submit" type="button" value="上传文档" onclick="uploadDOC()"></p>
								 </form>
				 			</div>
				 			</s:if>
						</div>
					</s:if>
					<s:if test="#dataType==3">
						<div id="audiolTool">
							<p>模块信息:用来上传教学音频。</p>
							<s:if test="#session.user.userType==1 or #session.user.userType==2">
							<div class="fileForm">
								<form id="audioForm" action="fileHandleAction_uploadAUDIO.action" method="post" enctype="multipart/form-data">
								<input type="hidden" name="userId" value="${userId}">
					 			<p>标题: <input class="title" type="text" name="title"></p> 
					    			<p> <input id="audioFile" class="file" type="file" name="file"></p>
					     		 	<p class="uploadType" title="mp3">支持:&nbsp;mp3</p>
					     		 	<p> <input class="submit" type="button" value="上传音频" onclick="uploadAUDIO()"></p>
								 </form>
				 			</div>
				 			</s:if>
						</div>
					</s:if>
					<s:if test="#dataType==4">
						<div id="imageTool">
							<p>模块信息:用来上传教学图片。</p>
							<s:if test="#session.user.userType==1 or #session.user.userType==2">
							<div class="fileForm">
								<form id="imgForm"  action="fileHandleAction_uploadIMG.action" method="post" enctype="multipart/form-data">
								<input type="hidden" name="userId" value="${userId}">
					 			<p>标题: <input class="title" type="text" name="title"></p> 
					    			<p> <input id="imgFile" class="file" type="file" name="file"></p>
					    			<p class="uploadType" title="BMP、JPG、JPEG、PNG、GIF">支持:&nbsp;BMP、JPG、JPEG、PNG、GIF</p>
					     		 	<p> <input class="submit" type="button" value="上传图片" onclick="uploadIMG()"></p>
								 </form>
				 			</div>
				 			</s:if>
						</div>
					</s:if>
					<s:if test="#dataType==5">
						<div id="otherTool">
							<p>模块信息:用来上传其他资料。只提供上传和下载功能</p>
							<s:if test="#session.user.userType==1 or #session.user.userType==2">
							<div class="fileForm">
								<form id="otherForm"  action="fileHandleAction_uploadOTHER.action" method="post" enctype="multipart/form-data">
								<input type="hidden" name="userId" value="${userId}">
					 			<!-- <p>标题: <input class="title" type="text" name="title"></p>  -->
					    			<p> <input id="otherFile" class="file" type="file" name="file"></p>
					    			<p class="uploadType" title="文件类型不限">文件类型不限</p>
					     		 	<p> <input class="submit" type="button" value="上传资料" onclick="uploadOTHER()"></p>
								 </form>
				 			</div>
				 			</s:if>
						</div>
					</s:if>
				</div>
				<div id="date">
					<p id="nowTime">当前时间:<fmt:formatDate value="<%=new Date()%>" pattern="HH:mm:ss" /><p>
					<!-- 日历开始 -->
					<jsp:include page="/date.jsp"></jsp:include>
				</div>
			</div>
		</div>
	</div>
	<div class="pageRollFile">
			<jsp:include page="/WEB-INF/jsp/home/pageRoll.jsp" flush="true"></jsp:include>
			<%-- <jsp:param value="userAction_list" name="action"/> 不需要为空的话默认地址栏--%>
	</div>
</body>
</html>
