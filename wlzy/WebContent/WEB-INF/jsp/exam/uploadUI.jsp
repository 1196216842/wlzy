<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>上传</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<style type="text/css">
#upload{width:100%;height:100%;margin: 0px auto;text-align: center;}
#upload img{width:700px;height:350px;margin-bottom: 10px;}

</style>
</head>
<body>
	<div id="list">
		<div class="top"><jsp:include page="/top.jsp"></jsp:include></div>
		<div class="nav">
			<a href="#">当前位置：</a>
			<a href="homeAction_list.action">首页<img
				src="${pageContext.request.contextPath }/images/arrow_close.gif" />
			</a> <a href="examTopicAction_list.action">在线测试<img
				src="${pageContext.request.contextPath }/images/arrow_close.gif" />
			</a><a>上传试题</a>
		</div>
		<div class="content">
			<div id="upload">
				<div><p>模板(Excel)</p><img src="${pageContext.request.contextPath }/images/exam.jpg"/></div>
				<form id="form" action="examTopicAction_upload.action" method="post" enctype="multipart/form-data">
					<input id="file" type="file" name="file"/><font color="red">
					<input type="button"  value="上传" onclick="upload()"/>${requestScope.message}</font>
				</form>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
function upload(){
		
		 var value=$("#file").val();
		var type=value.substring(value.lastIndexOf(".")+1).toLowerCase();
		var flag=(type=="xls"||type=="xlsx");
		if(flag){
			//可以上传
			$("#form").submit();
		}else{
			alert("对不起，类型为"+type+"的文件不符合规范Excel类型的文件");
		} 
	}

</script>
</html>
