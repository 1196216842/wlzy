<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>物理天地</title>
<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0, width=device-width"/> 
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/vedio.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/style.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/video-js.css" />
<style type="text/css">
/* #show{
	display:none;
} */
/* #teacher{
	float: left;
} */
/* #all{
	float: left;
} */


#list {
	width:100%;
}
#listTop{
	width:100%;
}
#type{
	margin-left:530px;
}
#content{
	height: 70%;
	margin-top: 15px;
	text-align: center;
	margin-left: 60px;
}
 #select{
	float: left;
	margin-left: 420px;
} 
#divForm{
	float: left;
	margin-left: 
}
#img1{
	width: 170px;
	height: 130px;
}
font{
	color:blue;
}
.vedio{
	float:left;
	width:180px;
	height:140px;
	margin-left: 30px;
	margin-bottom: 70px;
}
.image{
	float:left;
	width:180px;
	height:180px;
	margin-left: 30px;
	margin-bottom: 30px;
}
.audio{
	float:left;
	width:180px;
	height:180px;
	margin-left: 130px;
	margin-bottom: 30px;
}
.doc{
	float:left;
	width:180px;
	height:180px;
	margin-left: 40px;
	margin-bottom: 30px;
}
#music{
	width: 290px;
	height: 40px;
}
</style>

<script type="text/javascript">
$(function(){
	/* $("#teacher").mousemove(function(){
		$("#show").show();
	});
	$("#teacher").mouseleave(function(){
		$("#show").hide();
	}); */
	$("#divForm div").hide();
	$("#divForm #videoForm").show();
	$("#divForm #videoForm").css("float","left");
	
	$("#select1").change(function(){
		var userId=$("#select1").val();
		window.location="myFileAction_list.action?userId="+userId+"&fileType=4";
	});
	
	$("#select2").change(function(){
		var value=$("#select2").val();
		if(value==4){//视频
			$("#divForm div").hide();
		    $("#divForm #videoForm").show();
		}else if(value==2){//文档
			$("#divForm div").hide();
		    $("#divForm #docForm").show();
		}else if(value==3){//音频
			$("#divForm div").hide();
		    $("#divForm #audioForm").show();
		}else{//图片
			$("#divForm div").hide();
		    $("#divForm #imgForm").show();
		}
	});
	
});
</script>
</head>

<body>
<%-- 
	<div id="user">
		<div id="all"><s:a action="myFileAction_list.action?userId=0&fileType=4">全部</s:a></div>
		<div id="teacher">
			<div>老师</div>
			<div id="show">
				<s:iterator value="#teachers">
					<div><s:a action="myFileAction_list.action?userId=%{id}&fileType=4">&nbsp;&nbsp;${username}</s:a></div>
				</s:iterator>
			</div>
		</div>
	</div> --%>
<div id="list">

	<!-- --------------------------------------------- -->
		<!-- 显示标题 -->
		<div id="listTop">
			<div id="user">
			 	请选择：<select id="select1">
					  <s:if test="#userId==0">
						<option selected="selected" value="0">全部</option>
					  </s:if>
					  <s:else>
						<option value="0">全部</option>
				      </s:else>
				     <s:iterator value="#teachers">
					  <s:if test="#userId==id">
						<option selected="selected"  value="${id}">
						${username}
						</option>
					  </s:if>
					  <s:else>
						<option value="${id}">
							${username}
						</option>
					 </s:else>
				     </s:iterator>
			      </select>
			 </div>
			<!-- 需要从服务器把查询哪一个老师的ID返回回来 ，不然这个地方不好搞-->
			<div id="type">
				<s:a id="video" action="myFileAction_list.action?userId=%{#userId}&fileType=4"><font size="3em">视频</font></s:a>
				<s:a id="doc" action="myFileAction_list.action?userId=%{#userId}&fileType=2"><font size="3em">文档</font></s:a>
				<s:a id="audio" action="myFileAction_list.action?userId=%{#userId}&fileType=3"><font size="3em">音频</font></s:a>
				<s:a id="img" action="myFileAction_list.action?userId=%{#userId}&fileType=1"><font size="3em">图片</font></s:a>
			</div>
		</div>
		<!-- -------------------------------------------------------------------- -->
		<!-- 显示内容 -->
		<div id="content">
			<!-- <table id="table1"> -->
			<s:iterator value="#myFiles">
				<!-- 图片 -->
				<s:if test="fileType==1">
						<div class=image>
						<div>
							<div>${trueName}</div>
							<div><img id="img1" src="${pageContext.request.contextPath}/upload/images/${uuidName}" /></div>
  						</div>
						<div style="width: 200px"> 
						<table>
  							<tr>
  								<td width="40px" align="right"><font size="2em">时间：</font></td>
  								<td width="150px"><font size="2em">${uploadTime}</font></td>
  							</tr>
  						     <tr>
  								<td width="40px" align="left"><font size="2em">作者：</font></td>
  								<td width="150px" align="left">
  								<font size="2em"><s:property value="user.username"/></font>
  								<s:a action="myFileAction_delete?id=%{id}&userId=%{#userId}&fileType=1&uuidName=%{uuidName}"><font size="2em">删除</font></s:a>
									<s:a action="myFileAction?uuidName=%{uuidName}&fileType=%{fileType}&trueName=%{trueName}"><font size="2em">下载</font></s:a>
  								</td>
  							</tr> 
  						</table>
						</div>
					
					</div>
					</s:if>
				<!-- 视频 -->
				<s:if test="fileType==4">
					<div class=vedio>
						<div>
							${trueName}
							<video  width="180" height="140" controls>  
					       	<source src="${pageContext.request.contextPath}/upload/videos/${uuidName}"></source> 
	  						</video> 
  						</div>
  						
						<div style="width: 200px"> 
						<table>
  							<tr>
  								<td width="40px" align="right"><font size="2em">时间：</font></td>
  								<td width="150px"><font size="2em">${uploadTime}</font></td>
  							</tr>
  						     <tr>
  								<td width="40px" align="left"><font size="2em">作者：</font></td>
  								<td width="150px" align="left">
  								<font size="2em"><s:property value="user.username"/></font>
  								<s:a action="myFileAction_delete?id=%{id}&userId=%{#userId}&fileType=4&uuidName=%{uuidName}"><font size="2em">删除</font></s:a>
									<s:a action="myFileAction?uuidName=%{uuidName}&fileType=%{fileType}&trueName=%{trueName}"><font size="2em">下载</font></s:a>
  								</td>
  							</tr> 
  						</table>
						</div>
					
					</div>
				</s:if>
				<!-- 音频 -->
				<s:if test="fileType==3">
				<div class=audio>
						<div>
							${trueName}
							 <audio id="music" controls="controls" src="${pageContext.request.contextPath}/upload/audios/${uuidName}" ></audio>   
  						</div>
  						
						<div style="width: 200px"> 
						<table>
  							<tr>
  								<td width="40px" align="right"><font size="2em">时间：</font></td>
  								<td width="150px"><font size="2em">${uploadTime}</font></td>
  							</tr>
  						     <tr>
  								<td width="40px" align="left"><font size="2em">作者：</font></td>
  								<td width="150px" align="left">
  								<font size="2em"><s:property value="user.username"/></font>
  								<s:a action="myFileAction_delete?id=%{id}&userId=%{#userId}&fileType=3&uuidName=%{uuidName}"><font size="2em">删除</font></s:a>
									<s:a action="myFileAction?uuidName=%{uuidName}&fileType=%{fileType}&trueName=%{trueName}"><font size="2em">下载</font></s:a>
  								</td>
  							</tr> 
  						</table>
						</div>
					
					</div>
				</s:if>
				<!-- 文档 -->
				<s:if test="fileType==2">
					<div class=doc>
						<div>
							<font size="2em">${trueName}</font>
  						</div>
						<div style="width: 200px"> 
						<table>
  							<tr>
  								<td width="40px" align="right"><font size="2em">时间：</font></td>
  								<td width="150px"><font size="2em">${uploadTime}</font></td>
  							</tr>
  						     <tr>
  								<td width="40px" align="left"><font size="2em">作者：</font></td>
  								<td width="150px" align="left">
  								<font size="2em"><s:property value="user.username"/></font>
  								 <s:a action="myFileAction_readDoc?uuidName=%{uuidName}&id=%{id}"><font size="2em">查看</font></s:a> 
  								<s:a action="myFileAction_delete?id=%{id}&userId=%{#userId}&fileType=2&uuidName=%{uuidName}"><font size="2em">删除</font></s:a>
									<s:a action="myFileAction?uuidName=%{uuidName}&fileType=%{fileType}&trueName=%{trueName}"><font size="2em">下载</font></s:a>
  								</td>
  							</tr> 
  						</table>
						</div>
					
					</div>
				</s:if> 
			</s:iterator>
		</div>
		<br/>
		<br/>
		<!-- --------------------------------------------------------------------- -->
		 <div id="select">
			上传：类型
			<select id="select2">
				<option value="4" selected="selected">视频</option>
				<option value="2">文档</option>
				<option value="3">音频</option>
				<option value="1">图片</option>
			</select>
		</div>
		<!-- --------------------------------------------------------------------- -->
		<div id="divForm">
				<div id="imgForm">
				 	<form  action="myFileAction_uploadIMG.action" method="post" enctype="multipart/form-data">
						<s:hidden name="userId" value="0"></s:hidden>
						<s:hidden name="fileTyle" value="4"></s:hidden>
					标题: <input type="text" name="title"><br> 
				             图片: <input type="file" name="file"><br>
				    	 <input type="submit" value="submit">
				    </form>
				 </div>
				 <div id="docForm">
					<form  action="myFileAction_uploadDOC.action" method="post" enctype="multipart/form-data">
					 标题: <input type="text" name="title"><br> 
					 文档: <input type="file" name="file"><br>
					     <input type="submit" value="submit">
					      <s:hidden name="userId" value="0"></s:hidden>
					</form>
				 </div>
				 <div id="audioForm">
				     <form  action="myFileAction_uploadAUDIO.action" method="post" enctype="multipart/form-data">
					  标题: <input type="text" name="title"><br> 
					  音频: <input type="file" name="file"><br>
					      <input type="submit" value="submit">
					       <s:hidden name="userId" value="0"></s:hidden>
					 </form>
				 </div>
				 <div id="videoForm">
					<form action="myFileAction_uploadVIDEO.action" method="post" enctype="multipart/form-data">
					 	标题: <input type="text" name="title"><br> 
					     视频: <input type="file" name="file"><br>
					      <input type="submit" value="submit">
					       <s:hidden name="userId" value="0"></s:hidden>
					 </form>
				 </div>	 
		</div> 
		<!-- ------------------------------------------------- -->
</div>
</body>
</html>
